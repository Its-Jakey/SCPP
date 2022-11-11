#include <map>
#include <strings>
#include <input>
#include <stringList>
#include <numbers>
#include <arrays>
#include "filesystem.sc"
#include "state.sc"
#include "utils.sc"


#define HLT 0
#define LD 1
#define LDI 2
#define ST 3
#define JMP 4
#define AND 5
#define OR 6
#define ADD 7
#define SUB 8
#define MUL 9
#define DIV 10
#define JZ 11
#define JNZ 12
#define JC 13
#define JNC 14
#define CMP 15
#define STZ 16
#define CLZ 17
#define STC 18
#define CLC 19
#define PSH 20
#define POP 21
#define CALL 22
#define RET 23
#define SYSCALL 24
#define MOV 25
#define STE 26
#define CLE 27
#define JE 28
#define JNE 29
#define LDF 30
#define STF 31
#define INC 32
#define DEC 33
#define CON 34
#define LDP 35
#define STP 36


#define STACK_START 0xE000
#define STACK_END 0xF000

#define SYSTEM_VARIABLE_START 0xFE00
#define SYSTEM_VARIABLE_END 0xFF00

#define FILES_START 0xFE00
#define FILES_END 0xFE20

#define DIRECTORYS_START 0xFE20
#define DIRECTORYS_END 0xFE40

#define ARGS_LENGTH 0xFE40
#define ARGS_START 0xFE41
#define ARGS_END 0xFE60

#define SYSCALL_READ_LINE 0
#define SYSCALL_PRINT_STRING 1
#define SYSCALL_GET_CURRENT_DIRECTORY 2
#define SYSCALL_SET_CURRENT_DIRECTORY 3
#define SYSCALL_LIST_FILES 4
#define SYSCALL_CREATE_FILE 5
#define SYSCALL_READ_FROM_FILE 6
#define SYSCALL_WRITE_TO_FILE 7
#define SYSCALL_CREATE_DIRECTORY 8
#define SYSCALL_FILE_EXISTS 9
#define SYSCALL_GET_ABSOLUTE_PATH 10
#define SYSCALL_FILE_IS_DIRECTORY 11
#define SYSCALL_NEW_LINE 12
#define SYSCALL_GET_PARENT_DIRECTORY 13
#define SYSCALL_REMOVE_FILE 14


namespace labels is map;
namespace words is stringList;

public namespace assembler {
    var collectOutput;
    var pass1;
    var output;

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

    func getLabel(label) {
        if (pass1)
            return 0;
        var ret = labels::get(label);
        
        if (ret == -1)
            println("Unknown label '"..label.."'");
        return ret;
    }

    var line;
    func getVal(val) {
        if (strings::startsWith(val, "@"))
            return getLabel(strings::substring(val, 1));
        if (strings::startsWith(val, "0x"))
            return numbers::toBase10(strings::substring(val, 2), 16);
        return val;
    }

    func pass(toAssemble) {
        var program = strings::split(toAssemble, "\n");
        var size = strings::splitSize;
        line = 0;

        while (line < size) {
            var tmp = program[line];

            while (strings::charAt(tmp, 0) == " ")
                tmp = strings::substring(tmp, 1);

            var command = utils::splitCommand(tmp, " ");
            var argLen = utils::splitSize;

            if (argLen == 0) {}
            else if (strings::startsWith(command[0], ";")) {}
            else if (strings::startsWith(command[0], ":")) {
                if (pass1)
                    labels::put(strings::substring(command[0], 1), words::size());
            } else if (strings::startsWith(command[0], ".")){
                switch (command[0]) {
                    case ".define" -> {
                        if (pass1)
                            labels::put(getVal(command[1]), getVal(command[2]));
                    }
                    case ".include" -> {
                        var text = fs::readFromFile(getVal(command[1]));
                        var newlines = strings::split(text, "\n");

                        program = arrays::combineArray(program, size, newlines, strings::splitSize);
                        size += strings::splitSize;
                    }
                    case ".addr" -> {
                        for (j from words::size() to getVal(command[1]))
                            words::add(0);
                    }
                }
            } else {
                var asm = command[0];

                if (argLen == 1) {
                    switch (asm) {
                        case "HLT" -> words::add(HLT);
                        case "STZ" -> words::add(STZ);
                        case "CLZ" -> words::add(CLZ);
                        case "STC" -> words::add(STC);
                        case "CLC" -> words::add(CLC);
                        case "STE" -> words::add(STE);
                        case "CLE" -> words::add(CLE);
                        case "RET" -> words::add(RET);
                        default -> {
                            println("Unknown command: "..tmp);
                            return;
                        }
                    }
                } else if (argLen == 2) {
                    switch (asm) {
                        case "JZ" -> {
                            words::add(JZ);
                            words::add(getVal(command[1]));
                        }
                        case "JNZ" -> {
                            words::add(JNZ);
                            words::add(getVal(command[1]));
                        }
                        case "JC" -> {
                            words::add(JC);
                            words::add(getVal(command[1]));
                        }
                        case "JNC" -> {
                            words::add(JNC);
                            words::add(getVal(command[1]));
                        }
                        case "JMP" -> {
                            words::add(JMP);
                            words::add(getVal(command[1]));
                        }
                        case "PSH" -> {
                            words::add(PSH);
                            words::add(getVal(command[1]));
                        }
                        case "POP" -> {
                            words::add(POP);
                            words::add(getVal(command[1]));
                        }
                        case "CALL" -> {
                            words::add(CALL);
                            words::add(getVal(command[1]));
                        }
                        case "INC" -> {
                            words::add(INC);
                            words::add(getVal(command[1]));
                        }
                        case "DEC" -> {
                            words::add(DEC);
                            words::add(getVal(command[1]));
                        }
                        case "JE" -> {
                            words::add(JE);
                            words::add(getVal(command[1]));
                        }
                        case "JNE" -> {
                            words::add(JNE);
                            words::add(getVal(command[1]));
                        }
                        case "SYSCALL" -> {
                            words::add(SYSCALL);
                            words::add(getVal(command[1]));
                        }
                        default -> {
                            println("Unknown command: "..tmp);
                            return;
                        }
                    }
                } else if (argLen == 3) {
                    switch (asm) {
                        case "LD" -> {
                            words::add(LD);
                            words::add(getVal(command[1]));
                            words::add(getVal(command[2]));
                        }
                        case "LDI" -> {
                            words::add(LDI);
                            words::add(getVal(command[1]));
                            words::add(getVal(command[2]));
                        }
                        case "ST" -> {
                            words::add(ST);
                            words::add(getVal(command[1]));
                            words::add(getVal(command[2]));
                        }
                        case "AND" -> {
                            words::add(AND);
                            words::add(getVal(command[1]));
                            words::add(getVal(command[2]));
                        }
                        case "OR" -> {
                            words::add(OR);
                            words::add(getVal(command[1]));
                            words::add(getVal(command[2]));
                        }
                        case "ADD" -> {
                            words::add(ADD);
                            words::add(getVal(command[1]));
                            words::add(getVal(command[2]));
                        }
                        case "SUB" -> {
                            words::add(SUB);
                            words::add(getVal(command[1]));
                            words::add(getVal(command[2]));
                        }
                        case "MUL" -> {
                            words::add(MUL);
                            words::add(getVal(command[1]));
                            words::add(getVal(command[2]));
                        }
                        case "DIV" -> {
                            words::add(DIV);
                            words::add(getVal(command[1]));
                            words::add(getVal(command[2]));
                        }
                    
                        case "CMP" -> {
                            words::add(CMP);
                            words::add(getVal(command[1]));
                            words::add(getVal(command[2]));
                        }
                        case "MOV" -> {
                            words::add(MOV);
                            words::add(getVal(command[1]));
                            words::add(getVal(command[2]));
                        }
                        case "CON" -> {
                            words::add(CON);
                            words::add(getVal(command[1]));
                            words::add(getVal(command[2]));
                        }
                        case "LDP" -> {
                            words::add(LDP);
                            words::add(getVal(command[1]));
                            words::add(getVal(command[2]));
                        }
                        case "STP" -> {
                            words::add(STP);
                            words::add(getVal(command[1]));
                            words::add(getVal(command[2]));
                        }
                        default -> {
                            println("Unknown command: "..tmp);
                            return;
                        }
                    }
                } else if (argLen == 4) {
                    switch (asm) {
                        case "LDF" -> {
                            words::add(LDF);
                            words::add(getVal(command[1]));
                            words::add(getVal(command[2]));
                            words::add(getVal(command[3]));
                        }
                        default -> {
                            println("Unknown command: "..tmp);
                            return;
                        }
                    }
                } else {
                    println("Unknown command '"..asm.."' with "..argLen.." arguments");
                    return;
                }

                
            }
            line++;
        }
    }
    var pc;
    var ram;
    var registers;
    func setAddress(address, value) {
        if (address > 0xFFFF) {
            println("Ram address "..address.." out of range. At PC: "..pc);
            exit();
        }
        ram[address] = value;
    }

    func getAddress(address) {
        if (address > 0xFFFF) {
            println("Ram address "..address.." out of range. At PC: "..pc);
            return;
        }
        return ram[address];
    }

    func run() {
        var sp = STACK_START;
        var running = 1;
        var c = 0;
        var z = 0;
        var e = 0;
        pc = 0;

        while (running) {
            var opcode = getAddress(pc);
            pc++;

            switch (opcode) {
                case HLT -> running = 0;
                case LD -> {
                    var reg = getAddress(pc);
                    pc++;
                    var val = getAddress(ram[pc]);
                    pc++;
                    registers[reg] = val;
                }
                case LDI -> {
                    var reg = getAddress(pc);
                    pc++;
                    var val = getAddress(pc);
                    pc++;
                    registers[reg] = val;

                    //println("LDI", reg, val);
                }
                case ST -> {
                    var reg = getAddress(pc);
                    pc++;
                    var addr = getAddress(pc);
                    pc++;
                    setAddress(addr, registers[reg]);
                }
                case JMP -> {
                    var addr = getAddress(pc);
                    pc = addr;
                }
                case AND -> {
                    var reg1 = getAddress(pc);
                    pc++;
                    var reg2 = getAddress(pc);
                    pc++;
                    registers[reg1] = registers[reg1] & registers[reg2];
                }
                case OR -> {
                    var reg1 = getAddress(pc);
                    pc++;
                    var reg2 = getAddress(pc);
                    pc++;
                    registers[reg1] = registers[reg1] | registers[reg2];
                }
                case ADD -> {
                    var reg1 = getAddress(pc);
                    pc++;
                    var reg2 = getAddress(pc);
                    pc++;
                    registers[reg1] = registers[reg1] + registers[reg2];
                }
                case SUB -> {
                    var reg1 = getAddress(pc);
                    pc++;
                    var reg2 = getAddress(pc);
                    pc++;
                    registers[reg1] = registers[reg1] - registers[reg2];
                }
                case MUL -> {
                    var reg1 = getAddress(pc);
                    pc++;
                    var reg2 = getAddress(pc);
                    pc++;
                    registers[reg1] = registers[reg1] * registers[reg2];
                }
                case DIV -> {
                    var reg1 = getAddress(pc);
                    pc++;
                    var reg2 = getAddress(pc);
                    pc++;
                    registers[reg1] = registers[reg1] / registers[reg2];
                }
                case JZ -> {
                    var addr = getAddress(pc);
                    pc++;
                    //println(z);
                    if (z) pc = addr;
                }
                case JNZ -> {
                    var addr = getAddress(pc);
                    pc++;
                    if (z == 0) pc = addr;
                }
                case JC -> {
                    var addr = getAddress(pc);
                    pc++;
                    if (c) pc = addr;
                }
                case JNC -> {
                    var addr = getAddress(pc);
                    pc++;
                    if (c == 0) pc = addr;
                }
                case CMP -> {
                    var reg1 = getAddress(pc);
                    pc++;
                    var reg2 = getAddress(pc);
                    pc++;

                    z = registers[reg1] == registers[reg2];
                    c = registers[reg1] < registers[reg2];
                }
                case STZ -> z = 1;
                case CLZ -> z = 0;
                case STC -> c = 1;
                case PSH -> {
                    var reg = getAddress(pc);
                    pc++;
                    sp--;
                    setAddress(sp, registers[reg]);
                }
                case POP -> {
                    var reg = getAddress(pc);
                    pc++;
                    registers[reg] = getAddress(sp);
                    sp++;
                }
                case CALL -> {
                    var addr = getAddress(pc);
                    pc++;
                    sp--;
                    setAddress(sp, pc);
                    pc = addr;
                }
                case RET -> {
                    pc = getAddress(sp);
                    sp++;
                }
                case SYSCALL -> {
                    var call = getAddress(pc);
                    pc++;

                    switch (call) {
                        case SYSCALL_READ_LINE -> registers[15] = input::ask("");
                        case SYSCALL_PRINT_STRING -> wol(registers[15]);
                        case SYSCALL_GET_CURRENT_DIRECTORY -> registers[15] = state::currentDir;
                        case SYSCALL_SET_CURRENT_DIRECTORY -> state::currentDir = registers[15];
                        case SYSCALL_LIST_FILES -> {
                            file::unpack(fs::getFile(registers[15]));

                            var fileAddr = FILES_START + 1;
                            var dirs = 0;

                            for (i from 0 to file::childrenCount) {
                                var child = file::children[i];

                                if (child[1]) { //If file is a directory
                                    setAddress(dirs + DIRECTORYS_START + 1, child[0]);
                                    dirs++;
                                } else {
                                    setAddress(fileAddr, child[0]);
                                    fileAddr++;
                                }
                            }
                            setAddress(FILES_START, fileAddr - FILES_START - 1);
                            setAddress(DIRECTORYS_START, dirs);
                        }
                        case SYSCALL_CREATE_FILE -> fs::createFile(registers[15]);
                        case SYSCALL_READ_FROM_FILE -> registers[15] = fs::readFromFile(registers[15]);
                        case SYSCALL_WRITE_TO_FILE -> fs::writeToFile(registers[14], registers[15]);
                        case SYSCALL_CREATE_DIRECTORY -> fs::createDirectory(registers[15]);
                        case SYSCALL_FILE_EXISTS -> e = fs::exists(registers[15]);
                        case SYSCALL_GET_ABSOLUTE_PATH -> registers[15] = state::getAbsolutePath(registers[15]);
                        case SYSCALL_FILE_IS_DIRECTORY -> e = fs::isDirectory(registers[15]);
                        case SYSCALL_NEW_LINE -> wol("");
                        case SYSCALL_GET_PARENT_DIRECTORY -> registers[15] = fs::getParentDirectory(registers[15]);
                        case SYSCALL_REMOVE_FILE -> fs::removeFile(registers[15]);
                    }
                }
                case MOV -> {
                    var reg1 = getAddress(pc);
                    pc++;
                    var reg2 = getAddress(pc);
                    pc++;
                    registers[reg1] = registers[reg2];
                }
                case STE -> e = 1;
                case CLE -> e = 0;
                case JE -> {
                    var addr = getAddress(pc);
                    pc++;
                    if (e) pc = addr;
                }
                case JNE -> {
                    var addr = getAddress(pc);
                    pc++;
                    if (e == 0) pc = addr;
                }
                case LDF -> {
                    var reg = getAddress(pc);
                    pc++;
                    var addr = getAddress(pc);
                    pc++;
                    var offset = getAddress(pc);
                    pc++;

                    registers[reg] = getAddress(addr + registers[offset]);
                }
                case STF -> {
                    var reg = getAddress(pc);
                    pc++;
                    var addr = getAddress(pc);
                    pc++;
                    var offset = getAddress(pc);
                    pc++;

                    setAddress(addr + registers[offset], registers[reg]);
                }
                case INC -> {
                    var reg = getAddress(pc);
                    pc++;
                    registers[reg] = registers[reg] + 1;
                }
                case DEC -> {
                    var reg = getAddress(pc);
                    pc++;
                    registers[reg] = registers[reg] - 1;
                }
                case CON -> {
                    var regA = getAddress(pc);
                    pc++;
                    var regB = getAddress(pc);
                    pc++;

                    registers[regA] = registers[regA]..registers[regB];
                }
                case LDP -> {
                    var destReg = getAddress(pc);
                    pc++;
                    var srcReg = getAddress(pc);
                    pc++;
                    registers[destReg] = getAddress(registers[srcReg]);
                }
            }
        }
    }

    func initEmu() {
        ram = malloc(0xFFFF);
        registers = malloc(16);
    }

    public func execute(bin, collectOutput_) {
        collectOutput = collectOutput_;
        ram = malloc(0xFFFF);
        registers = malloc(16);
        var instructions = strings::split(bin, "\n");

        for (i from 0 to strings::splitSize)
            setAddress(i, instructions[i]);
        free(bin, 2);

        run();
        free(ram, 0xFFFF);
        free(registers, 16);

        return output;
    }

    public func execute(bin, collectOutput_, args, argLen) {
        initEmu();
        collectOutput = collectOutput_;
        var instructions = strings::split(bin, ";");
        var instruction_n = strings::splitSize;

        for (i from 0 to instruction_n)
            setAddress(i, instructions[i]);
        free(instructions, instruction_n);

        setAddress(ARGS_LENGTH, argLen);
        for (i from 0 to argLen)
            setAddress(ARGS_START + i, args[i]);
        
        free(args, argLen);

        //for (i from 0 to 128)
        //    print(ram[i].." ");

        run();
        free(ram, 0xFFFF);
        free(registers, 16);

        return output;
    }

    func addConstants() {
        labels::put("READ_LINE", SYSCALL_READ_LINE);
        labels::put("PRINT_STRING", SYSCALL_PRINT_STRING);
        labels::put("GET_CURRENT_DIRECTORY", SYSCALL_GET_CURRENT_DIRECTORY);
        labels::put("SET_CURRENT_DIRECTORY", SYSCALL_SET_CURRENT_DIRECTORY);
        labels::put("LIST_FILES", SYSCALL_LIST_FILES);
        labels::put("CREATE_FILE", SYSCALL_CREATE_FILE);
        labels::put("READ_FROM_FILE", SYSCALL_READ_FROM_FILE);
        labels::put("WRITE_TO_FILE", SYSCALL_WRITE_TO_FILE);
        labels::put("CREATE_DIRECTORY", SYSCALL_CREATE_DIRECTORY);
        labels::put("FILE_EXISTS", SYSCALL_FILE_EXISTS);
        labels::put("GET_ABSOLUTE_PATH", SYSCALL_GET_ABSOLUTE_PATH);
        labels::put("FILE_IS_DIRECTORY", SYSCALL_FILE_IS_DIRECTORY);
        labels::put("NEW_LINE", SYSCALL_NEW_LINE);
        labels::put("GET_PARENT_DIRECTORY", SYSCALL_GET_PARENT_DIRECTORY);
        labels::put("REMOVE_FILE", SYSCALL_REMOVE_FILE);

        labels::put("STACK_START", STACK_START);
        labels::put("FILES_START", FILES_START);
        labels::put("DIRECTORYS_START", DIRECTORYS_START);
        labels::put("ARGS_START", ARGS_START);

        labels::put("STACK_END", STACK_END);
        labels::put("FILES_END", FILES_END);
        labels::put("DIRECTORYS_END", DIRECTORYS_END);
        labels::put("ARGS_END", ARGS_END);

        labels::put("ARGS_LENGTH", ARGS_LENGTH);
        for (i from 0 to 10)
            labels::put("ARG_"..i, i + ARGS_START);

        for (i from 0 to 16)
            labels::put("R"..i, i);
    }

    public func assemble(asm) {
        words::clear();
        labels::clear();

        pass1 = 1;
        addConstants();
        pass(asm);

        words::clear();
        addConstants();
        pass1 = 0;
        pass(asm);

        return words::getOutput();
    }
}