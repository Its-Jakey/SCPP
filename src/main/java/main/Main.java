package main;

import compiler.Compiler;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws Exception {
        Compiler.compile(Path.of("test.sc"));
        Files.writeString(Path.of("/home/jacob/Downloads/SCPPProgram.txt"), Compiler.getOutput());
        Files.writeString(Path.of("output.txt"), Compiler.getOutput());
    }
}
