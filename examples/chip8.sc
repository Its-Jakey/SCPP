#include <graphics>

namespace chip8 {
    var opcode;
    var memory = malloc(4096);
    var V = malloc(16);
    var I;
    var pc;
    var gfx = malloc(2048);
    var delay_timer;
    var sound_timer;
    var stack = malloc(16);
    var sp;
    var key = malloc(16);

    //0x000-0x1FF - Chip 8 interpreter (contains font set in emu)
    //0x050-0x0A0 - Used for the built in 4x5 pixel font set (0-F)
    //0x200-0xFFF - Program ROM and work RAM

    func render() {
        for (x from 0 to 64)
            for (y from 0 to 32) {
                graphics::setColor(gfx[(x + (y * 64))] * 0xFFFFFF);
                graphics::putPixel(x, y);
            }
        graphics::flip();
    }

    func execute(opcode) {
        var x = (opcode & 0x0F00) >> 8;
        var y = (opcode & 0x00F0) >> 4;
        var n = opcode & 0x000F;
        var nn = opcode & 0x00FF;
        var nnn = opcode & 0x0FFF;

        switch (opcode) {
            case 0x00E0 -> {
                //clear the display
                for (i from 0 to 2048)
                    gfx[i] = 0;
                return;
            }
            case 0x00EE -> {
                //return from subroutine
                sp--;
                pc = stack[sp];
                return;
            }
        }

        switch (opcode & 0xF000) {
            case 0x0000 ->
                println("RCA 1802 call instruction not implemented.");
            case 0x1000 ->
                //jump to address nnn
                pc = nnn;
            case 0x2000 -> {
                //call subroutine at nnn
                stack[sp] = pc;
                sp++;
                pc = nnn;
            }
            case 0x3000 -> {
                //skip next instruction if Vx == kk
                if (V[x] == nn)
                    pc = pc + 2;
            }
            case 0x4000 -> {
                //skip next instruction if Vx != kk
                if (V[x] != nn)
                    pc = pc + 2;
            }
            case 0x5000 -> {
                //skip next instruction if Vx == Vy
                if (V[x] == V[y])
                    pc = pc + 2;
            }
            case 0x6000 -> {
                //set Vx = kk
                V[x] = nn;
            }
            case 0x7000 -> {
                //set Vx = Vx + kk
                V[x] = V[x] + nn;
            }
            case 0x8000 -> {
                switch (n) {
                    case 0x0 -> {
                        //set Vx = Vy
                        V[x] = V[y];
                    }
                    case 0x1 -> {
                        //set Vx = Vx OR Vy
                        V[x] = V[x] | V[y];
                    }
                    case 0x2 -> {
                        //set Vx = Vx AND Vy
                        V[x] = V[x] & V[y];
                    }
                    case 0x3 -> {
                        //set Vx = Vx XOR Vy
                        //TODO: Find a way to do XOR
                        //V[x] = V[x] ^ V[y];
                    }
                    case 0x4 -> {
                        //add Vy to Vx, set VF = carry
                        var sum = V[x] + V[y];

                        V[0xF] = 0;
                        if (sum > 255)
                            V[0xF] = 1;
                        V[x] = sum;
                    }
                    case 0x5 -> {
                        //subtract Vy from Vx, set VF = NOT borrow
                        var diff = V[x] - V[y];

                        V[0xF] = 0;
                        if (diff < 0)
                            V[0xF] = 1;
                        V[x] = diff;
                    }
                    case 0x6 -> {
                        //shift Vx right by one, set VF = least significant bit of Vx before shift
                        V[0xF] = V[x] & 0x1;
                        V[x] = V[0xF] >> 1;
                    }
                    case 0x7 -> {
                        //set Vx = Vy - Vx, set VF = NOT borrow
                        var diff = V[y] - V[x];

                        V[0xF] = 0;
                        if (diff < 0)
                            V[0xF] = 1;
                        V[x] = diff;
                    }
                    case 0xE -> {
                        //shift Vx left by one, set VF = most significant bit of Vx before shift
                        V[0xF] = V[x] >> 7;
                        V[x] = V[x] << 1;
                    }
                }
            }
            case 0x9000 -> {
                //skip next instruction if Vx != Vy
                if (V[x] != V[y])
                    pc = pc + 2;
            }
            case 0xA000 -> {
                //set I = nnn
                I = nnn;
            }
            case 0xB000 -> {
                //jump to address nnn + V0
                pc = nnn + V[0];
            }
            case 0xC000 -> {
                //set Vx = random byte AND kk
                //V[x] = rand() & nn;
                //TODO: implement rand()
            }
            case 0xD000 -> {
                //draw sprite at coordinate (Vx, Vy) with width n
                var x2 = V[x];
                var y2 = V[y];
                var pixel;
                V[0xF] = 0;

                for (i from 0 to n) {
                    pixel = memory[I + i];

                    for (j from 0 to 8) {
                        if ((pixel & (0x80 >> j)) != 0) {
                            if (gfx[(x2 + j + ((y2 + i) * 64))] == 1)
                                V[0xF] = 1;
                            
                            var idx = x2 + j + ((y2 + i) * 64);
                            gfx[idx] = gfx[idx] < 1;
                        }
                    }
                }
                render();
            }
            case 0xE000 -> {
                switch (nn) {
                    case 0x009E -> {
                        //skip next instruction if key with the value of Vx is pressed
                        if (key[V[x]] != 0)
                            pc = pc + 2;
                    }
                    case 0x00A1 -> {
                        //skip next instruction if key with the value of Vx is not pressed
                        if (key[V[x]] == 0)
                            pc = pc + 2;
                    }
                }
            }
            case 0xF000 -> {
                switch (nn) {
                    case 0x0007 -> {
                        //set Vx = delay timer value
                        V[x] = delay_timer;
                    }
                    case 0x000A -> {
                        //wait for a key press, store the key in Vx
                        //TOOD: implement 'waitForKey'
                    }
                    case 0x0015 -> {
                        //set delay timer = Vx
                        delay_timer = V[x];
                    }
                    case 0x0018 -> {
                        //set sound timer = Vx
                        sound_timer = V[x];
                    }
                    case 0x001E -> {
                        //set I = I + Vx
                        I = I + V[x];
                    }
                    case 0x0029 -> {
                        //set I = location of sprite for digit Vx
                        I = V[x] * 5;
                    }
                    case 0x0033 -> {
                        //store BCD representation of Vx in memory locations I, I+1, and I+2
                        memory[I] = V[x] / 100;
                        memory[I + 1] = (V[x] / 10) % 10;
                        memory[I + 2] = (V[x] % 100) % 10;
                    }
                    case 0x0055 -> {
                        //store registers V0 through Vx in memory starting at location I
                        for (i from 0 to x + 1)
                            memory[I + i] = V[i];
                    }
                    case 0x0065 -> {
                        //read registers V0 through Vx from memory starting at location I
                        for (i from 0 to x + 1)
                            V[i] = memory[I + i];
                    }
                }
            }
        }
    }

    func cycle() {
        var highByte = memory[pc];
        var lowByte = memory[pc + 1];

        var opcode = (highByte << 8) + lowByte;

        //println(opcode);
        pc = pc + 2;
        execute(opcode);
    }

    func load_data(data, offset) {
        var i = 0;
        
        while (data[i] != 0xFFFFFF) {
            memory[i + offset] = data[i];
            i++;
        }
    }

    public func main() {
        var program = {
            0x00, 0xe0, 0xa2, 0x2a, 0x60, 0x0c, 0x61, 0x08, 0xd0, 0x1f, 0x70, 0x09, 0xa2, 0x39, 0xd0, 0x1f,
            0xa2, 0x48, 0x70, 0x08, 0xd0, 0x1f, 0x70, 0x04, 0xa2, 0x57, 0xd0, 0x1f, 0x70, 0x08, 0xa2, 0x66,
            0xd0, 0x1f, 0x70, 0x08, 0xa2, 0x75, 0xd0, 0x1f, 0x12, 0x28, 0xff, 0x00, 0xff, 0x00, 0x3c, 0x00,
            0x3c, 0x00, 0x3c, 0x00, 0x3c, 0x00, 0xff, 0x00, 0xff, 0xff, 0x00, 0xff, 0x00, 0x38, 0x00, 0x3f,
            0x00, 0x3f, 0x00, 0x38, 0x00, 0xff, 0x00, 0xff, 0x80, 0x00, 0xe0, 0x00, 0xe0, 0x00, 0x80, 0x00,
            0x80, 0x00, 0xe0, 0x00, 0xe0, 0x00, 0x80, 0xf8, 0x00, 0xfc, 0x00, 0x3e, 0x00, 0x3f, 0x00, 0x3b,
            0x00, 0x39, 0x00, 0xf8, 0x00, 0xf8, 0x03, 0x00, 0x07, 0x00, 0x0f, 0x00, 0xbf, 0x00, 0xfb, 0x00,
            0xf3, 0x00, 0xe3, 0x00, 0x43, 0xe0, 0x00, 0xe0, 0x00, 0x80, 0x00, 0x80, 0x00, 0x80, 0x00, 0x80,
            0x00, 0xe0, 0x00, 0xe0, 
            0xFFFFFF
        };
        var font = {
            0xF0, 0x90, 0x90, 0x90, 0xF0, // 0
            0x20, 0x60, 0x20, 0x20, 0x70, // 1
            0xF0, 0x10, 0xF0, 0x80, 0xF0, // 2
            0xF0, 0x10, 0xF0, 0x10, 0xF0, // 3
            0x90, 0x90, 0xF0, 0x10, 0x10, // 4
            0xF0, 0x80, 0xF0, 0x10, 0xF0, // 5
            0xF0, 0x80, 0xF0, 0x90, 0xF0, // 6
            0xF0, 0x10, 0x20, 0x40, 0x40, // 7
            0xF0, 0x90, 0xF0, 0x90, 0xF0, // 8
            0xF0, 0x90, 0xF0, 0x10, 0xF0, // 9
            0xF0, 0x90, 0xF0, 0x90, 0x90, // A
            0xE0, 0x90, 0xE0, 0x90, 0xE0, // B
            0xF0, 0x80, 0x80, 0x80, 0xF0, // C
            0xE0, 0x90, 0x90, 0x90, 0xE0, // D
            0xF0, 0x80, 0xF0, 0x80, 0xF0, // E
            0xF0, 0x80, 0xF0, 0x80, 0x80, // F
            0xFFFFFF
        };
        load_data(font, 0);
        load_data(program, 0x200);
        pc = 0x200;

        while (pc < 4096)
            cycle();
    }
}