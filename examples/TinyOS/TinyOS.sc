#include "filesystem.sc"
#include <input>
#include <graphics>
#include <strings>
#include <map>
#include "assembler.sc"
#include "state.sc"
#include "utils.sc"


namespace commands is map;

namespace OS {
    
    func execute(command, collectOutput) {
        var args = utils::splitCommand(command, " ");
        var argsLen = utils::splitSize;

        if (commands::get(args[0]) == -1)
            println("Command not found: "..args[0]);
        else {
            var commandArgs = arrays::createSubArray(args, 1, argsLen);
            return assembler::execute(fs::readFromFile(commands::get(args[0])), collectOutput, commandArgs, argsLen - 1);
        }
    }

    func start() {
        println("Welcome to TinyOS!");

        while (1)
            execute(input::ask(state::currentDir.." > "), 0);
    }

    func setupFS() {
        var bin = fs::createDirectory("/bin/");
        var etc = fs::createDirectory("/etc/");
        var home = fs::createDirectory("/home/");

        fs::writeToFile("/bin/test.bin", assembler::assemble("LDI 15 'Hello, world!'\nSYSCALL 1\nHLT"));
        fs::writeToFile("/bin/cd.bin", assembler::assemble(readFromLocalFile("fs/bin/cd.asm")));
        fs::writeToFile("/bin/cat.bin", assembler::assemble(readFromLocalFile("fs/bin/cat.asm")));
        fs::writeToFile("/bin/ls.bin", assembler::assemble(readFromLocalFile("fs/bin/ls.asm")));
        fs::writeToFile("/bin/touch.bin", assembler::assemble(readFromLocalFile("fs/bin/touch.asm")));
        fs::writeToFile("/bin/mkdir.bin", assembler::assemble(readFromLocalFile("fs/bin/mkdir.asm")));
        fs::writeToFile("/bin/rm.bin", assembler::assemble(readFromLocalFile("fs/bin/rm.asm")));
        fs::writeToFile("/bin/rmdir.bin", assembler::assemble(readFromLocalFile("fs/bin/rmdir.asm")));
        fs::writeToFile("/bin/mv.bin", assembler::assemble(readFromLocalFile("fs/bin/mv.asm")));
        fs::writeToFile("/bin/cp.bin", assembler::assemble(readFromLocalFile("fs/bin/cp.asm")));
        fs::writeToFile("/bin/echo.bin", assembler::assemble(readFromLocalFile("fs/bin/echo.asm")));
    }

    func setupCommands() {
        file::unpack(fs::getFile("/bin/"));

        for (i from 0 to file::childrenCount) {
            var name = strings::substring(file::children[i][0], 0, strings::lastIndexOf(file::children[i][0], "."));

            commands::put(name, "/bin/"..file::children[i][0]);
        }
    }

    public func main() {
        setupFS();
        setupCommands();
        start();
    }
}