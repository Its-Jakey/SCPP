#include <map>
#include <strings>
#include <arrays>
#include <input>
#include "stacks.sc"
#include "filesystem.sc"

#define RAM_SIZE 1024


namespace vars is map;
namespace labels is map;

public namespace tinyScript {
    var program;
    var ram;
    var ramPtr;
    var collectOutput;
    public var output;

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

    func wo(msg) {
        if (collectOutput)
            output ..= msg;
        else
            print(msg);
    }

    func wol(msg) {
        if (collectOutput)
            output ..= msg.."\n";
        else
            println(msg);
    }

    func getValue(val) {
        if (strings::startsWith(val, "@"))
            return ram[getVar(strings::substring(val, 1))];
        return val;
    }

    public func execute(program_, collectOutput_) {
        vars::clear();
        labels::clear();
        valueStack::clear();
        subStack::clear();
        ram = malloc(RAM_SIZE);
        ramPtr = 0;
        collectOutput = collectOutput_


        output = "";
        program = strings::split(program_, "\n");
        var programLen = strings::splitSize;

        var pc = 0;
        var a = 0;

        while (pc < programLen) {
            var command = utils::splitCommand(program[pc], " ");

            switch (command[0]) {
                case "var" -> createVar(command[1]);
                case "load" -> a = getValue(command[2]);
                case "store" -> ram[getVar(command[1])] = a;
                case "add" -> a += getValue(command[1]);
                case "sub" -> a -= getValue(command[1]);
                case "mul" -> a *= getValue(command[1]);
                case "div" -> a /= getValue(command[1]);
                case "mod" -> a %= getValue(command[1]);
                
                case "jmp" -> pc = labels::get(command[1]);
            }
        }
        return output;
    }
}