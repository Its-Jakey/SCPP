#include <strings>
#include <list>
#include "staticallyAllocatedStack.sc"

namespace tokens is list;

public namespace JSONLexer {
    var json;
    var char_n;
    var char;

    func next() {
        char = strings::charAt(json, char_n);
        char_n++;
    }

    func in(chars) {
        return strings::contains(chars, char);
    }

    func scan(chars) {
        var str = "";

        while (in(chars)) {
            str ..= char;
            next();
        }
        char_n--;
        return str;
    }

    func scanString(strEnd) {
        var str = "";
        next();

        while (char != strEnd) {
            str..=char;
            next();
        }
        return str;
    }

    public func lexToken() {
        if (in(" \t\n")) return;
        else if (in("0123456789.")) tokens::add({"int", scan("0123456789")});
        else if (in("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_")) tokens::add({"id", scan("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789")});
        else if (in("\"'")) tokens::add({"string", scanString(char)});
        else if (in("{}[],:")) tokens::add({char, char});
        else {
            println("Unexpected character '"..char.."'");
            return;
        }
    }

    public func lex(code) {
        tokens::new();
        json = code;
        char_n = 0;
        var len = strings::sizeOf(json);

        while (char_n < len) {
            next();

            if ((char_n < len) < 1)
                return;
            lexToken();
        }
    }

    public func printTokens() {
        for (i from 0 to tokens::size())
            println("["..tokens::get(i)[0]..", "..tokens::get(i)[1].."]");
    }
}

public namespace JSONObject {
    //JSONObject array format: {type: 'object', valuesLen, values: [{id, value: (JSONObject | JSONArray | JSONValue)}]}
    //JSONArrray array format: {type: 'array', valuesLen, values: [(JSONObject | JSONArray | JSONValue)]}
    //JSONValue format: {type: 'value', value: (str | int)}

    public var valuesLen;
    public var values;

    public func new() {
        valuesLen = 0;
        values = malloc(0);
    }

    public func addValue(id, value) {
        values = arrays::addToArray(values, valuesLen, {id, value});
        valuesLen++;
    }

    public func get(id) {
        for (i from 0 to valuesLen) {
            if (values[i][0] == id)
                return values[i][1];
        }
        return -1;
    }

    public func pack() {
        return {"object", valuesLen, values};
    }

    public func unpack(data) {
        if (data[0] != "object")
            println("Cannot unpack '"..data[0].."' into object");

        valuesLen = data[1];
        values = data[2];
    }

    public func replace(obj) {
        obj[0] = "object";
        obj[1] = valuesLen;
        obj[2] = values;
    }
}

public namespace JSONArray {
    //JSONArrray array format: {type: 'array', valuesLen, values: [(JSONObject | JSONArray | JSONValue)]}
    //JSONValue format: {type: 'value', value: (str | int)}

    public var valuesLen;
    public var values;

    public func new() {
        valuesLen = 0;
        values = malloc(0);
    }

    public func addValue(value) {
        values = arrays::addToArray(values, valuesLen, value);
        valuesLen++;
    }

    public func getValue(index) {
        return values[index];
    }

    public func pack() {
        return {"array", valuesLen, values};
    }

    public func unpack(data) {
        if (data[0] != "array")
            println("Cannot unpack '"..data[0].."' into array");

        valuesLen = data[1];
        values = data[2];
    }
}

public namespace JSONValue {
    //JSONValue format: {type: 'value', value: (str | int)}

    public var value;

    public func new(value_) {
        value = value_;
    }

    public func pack() {
        return {"value", value};
    }

    public func unpack(data) {
        if (data[0] != "value")
            println("Cannot unpack '"..data[0].."' into value");
        value = data[1];
    }
}

public namespace JSONParser {
    //JSONObject array format: {type: 'object', valuesLen, values: [{id, value: (JSONObject | JSONArray | JSONValue)}]}
    //JSONArrray array format: {type: 'array', valuesLen, values: [(JSONObject | JSONArray | JSONValue)]}
    //JSONValue format: {type: 'value', value: (str | int)}
    
    public var token_n;
    public var token;
    var layer = 0;

    func next() {
        token = tokens::get(token_n);
        token_n++;
    }

    func printl(str) {
        //println(strings::repeat("    ", layer)..str);
    }

    func parseObject(parent_) {
        var parent = parent_;

        if ((token_n < tokens::size()) < 1) {
            println("Reached end of JSON while parsing");
            return;
        }

        if (token[0] == "string") {
            var id = token[1];
            next();

            if (token[0] == ":") {
                next();
                
                printl("Parsing element '"..id.."'");

                staticallyAllocatedStack::push(parent);
                staticallyAllocatedStack::push(id);
                var obj = parseObject(-1);
                id = staticallyAllocatedStack::pop();
                parent = staticallyAllocatedStack::pop();

                printl("Parsed element");

                JSONObject::unpack(parent);
                JSONObject::addValue(id, obj);
                JSONObject::replace(parent);
                return;
                
            } else {
                printl("Added value '"..id.."'");

                JSONValue::new(id);
                return JSONValue::pack();
            }
        } else if (token[0] == "{") {
            JSONObject::new();
            var obj = JSONObject::pack();
            printl("Parsing object");
            layer++;

            while (1) {
                next();

                staticallyAllocatedStack::push(obj);
                parseObject(obj);
                obj = staticallyAllocatedStack::pop();

                if (token[0] != ",") {
                    layer--;
                    //printl("Object parsed");
                    printl(obj[0]);
                    return obj;
                }
            }
        } else
            println("Unexpected "..token[0]);
    }

    public func parse() {
        staticallyAllocatedStack::init();

        token_n = 0;
        next();
        return parseObject(-1);
    }
}