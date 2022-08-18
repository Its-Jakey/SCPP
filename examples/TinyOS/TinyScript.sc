#include <map>
#include <strings>
#include <arrays>
#include <input>
#include "stacks.sc"
#include "TinyLexer.sc"
#include "filesystem.sc"

#define RAM_SIZE 1024


namespace vars is map;
namespace labels is map;

public namespace tinyScript {
    var tokens;
    var pc;
    var token_n;
    var ram;
    var ramPtr;

    func next() {
        if (pc > token_n || pc == token_n)
            return -1;
        
        var ret = tokens[pc];
        pc++;
        return ret;
    }

    func createVar(name) {
        if (map::get(name) == -1) {
            map::put(name, ramPtr);
            ramPtr++;
        }
    }

    func getVar(name) {
        if (map::get(name) == -1)
            println("Unknown variable: ", name);
        //println(map::get(name));
        return map::get(name);
    }

    func pass1() {
        var token = next();

        while (token != -1) {
            if (token[0] == "label") {
                if (labels::get(token[1]) == -1)
                        labels::put(token[1], pc);
            }
            token = next();
        }
        pc = 0;
    }

    func getPath(path, currentDir) {
        if (path == ".")
            return currentDir;
        if (path == "..")
            return arrays::join(arrays::createSubArray(strings::split(path, "/"), 0, strings::splitSize - 1), strings::splitSize - 1, "/");
        if (strings::charAt(path, 0) == "/")
            return path;
        if (strings::charAt(path, strings::sizeOf(path) - 1) == "/")
            return concat(currentDir, path);
        return concat(currentDir, path, "/");
    }

    public func reset() {
        vars::clear();
        labels::clear();
        valueStack::clear();
        pc = 0;
        ram = malloc(RAM_SIZE);
        ramPtr = 0;
    }

    public func execute(tokens_, token_n_, dir) {
        tokens = tokens_;
        token_n = token_n_;
        pc = 0;
        pass1();

        var token = next();

        while (token != -1) {
            //println(token[0], token[1]);

            switch (token[0]) { //subStack::push() also pushes to valueStack
                case "int" -> valueStack::push(token[1]);
                case "string" -> valueStack::push(token[1]);
                case "label" -> {
                    
                }
                case "function" -> {
                    switch (token[1]) {
                        case "print" -> print(valueStack::pop());
                        case "println" -> println(valueStack::pop());
                        case "input" -> valueStack::push(input::ask(valueStack::pop()));
                        case "writeToFile" -> {
                            var data = valueStack::pop();
                            var name = valueStack::pop();
                            var path = valueStack::pop();

                            //println(path, name, data);

                            fs::writeToFile(path, name, data);
                        }
                        case "readFromFile" -> valueStack::push(fs::readFromFile(valueStack::pop()));
                        case "cr" -> print("\n");
                        case "dup" -> valueStack::push(valueStack::peek());
                        case "dump" -> for (i from 0 to valueStack::size()) print(valueStack::get(i), "");
                        case "charAt" -> {
                            var idx = valueStack::pop();
                            valueStack::push(strings::charAt(valueStack::pop(), idx));
                        }
                        case "sizeOf" -> valueStack::push(strings::sizeOf(valueStack::pop()));
                        case "getPath" -> {
                            valueStack::push(getPath(valueStack::pop(), dir));
                        }
                        case "alloc" -> {
                            var words = valueStack::pop();
                            valueStack::push(ramPtr);
                            ramPtr = ramPtr + words;
                        }
                        case "swap" -> {
                            var a = valueStack::pop();
                            var b = valueStack::pop();

                            valueStack::push(a);
                            valueStack::push(b);
                        }
                        case "splitString" -> {
                            var splitAt = valueStack::pop();
                            var arr = strings::split(valueStack::pop(), splitAt);
                            var ret = ramPtr;

                            for (i from 0 to strings::splitSize) {
                                ram[ramPtr] = arr[i];
                                ramPtr++;
                            }
                            valueStack::push(ret);
                            valueStack::push(strings::splitSize);
                        }
                        case "gotoif" -> {
                            token = next();

                            if (token[0] != "function") {
                                println("id expected after gotoif");
                                return;
                            }

                            var truePos = labels::get(token[1]);
                            if (tokens[pc][0] == "function" && tokens[pc][1] == "else") {
                                token = next();
                                token = next();


                                if (token[0] != "function") {
                                    println("id expected after else");
                                    return;
                                }
                                if (valueStack::pop())
                                    pc = truePos;
                                else {
                                        pc = labels::get(token[1]);
                                    }

                            } else if (valueStack::pop())
                                pc = truePos;
                        }
                        case "gosubif" -> {
                            token = next();

                            if (token[0] != "function") {
                                println("id expected after gosubif");
                                return;
                            }
                            subStack::push(pc);

                            truePos = labels::get(token[1]);
                            if (tokens[pc][0] == "function" && tokens[pc][1] == "else") {
                                token = next();
                                token = next();


                                if (token[0] != "function") {
                                    println("id expected after else");
                                    return;
                                }
                                if (valueStack::pop())
                                    pc = truePos;
                                else {
                                        pc = labels::get(token[1]);
                                    }

                            } else if (valueStack::pop()) {
                                pc = truePos;
                            } else
                                subStack::pop();
                        }
                        case "goto" -> {
                            token = next();

                            if (token[0] != "function") {
                                println("id expected after goto");
                                return;
                            }
                            pc = labels::get(token[1]);
                        }
                        case "gosub" -> {
                            token = next();

                            if (token[0] != "function") {
                                println("id expected after gosub");
                                return;
                            }
                            subStack::push(pc);
                            pc = labels::get(token[1]);
                        }
                        case "return" -> {
                            pc = subStack::pop();
                            if (pc == -1) {
                                println("No subroutine to return from");
                                return;
                            }
                        }
                        default -> println("Unknown function", token[1]);
                    }
                }
                case "varCreate" -> createVar(token[1]);
                case "varGet" -> valueStack::push(ram[getVar(token[1])]);
                case "varArrayGet" -> {
                    var offset = valueStack::pop();
                    valueStack::push(ram[ram[getVar(token[1])] + offset]);
                    //println("get", ram[getVar(token[1])] + offset, ram[ram[getVar(token[1])] + offset], valueStack::peek());
                }
                case "varSet" -> ram[getVar(token[1])] = valueStack::pop();
                case "varArraySet" -> {
                    offset = valueStack::pop();
                    var value = valueStack::pop();

                    ram[ram[getVar(token[1])] + offset] = value;
                    //println("set", ram[getVar(token[1])] + offset, ram[ram[getVar(token[1])] + offset]);
                }
                case "operator" -> {
                    b = valueStack::pop();
                    a = valueStack::pop();

                    switch (token[1]) {
                        case "+" -> valueStack::push(a + b);
                        case "-" -> valueStack::push(a - b);
                        case "*" -> valueStack::push(a * b);
                        case "/" -> valueStack::push(a / b);
                        case ">" -> valueStack::push(a > b);
                        case "<" -> valueStack::push(a < b);
                        case "=" -> valueStack::push(a == b);
                        case "." -> valueStack::push(concat(a, b));
                    }
                }
                default -> println("Unexpected", token[0], concat("with value of '", token[1], "'"), concat("at token #", pc - 1), "with", token_n, "tokens");
            }
            token = next();
        }
        free(ram, RAM_SIZE);
    }
}