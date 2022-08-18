package main;

import compiler.Compiler;
import org.apache.commons.cli.*;
import slvm.SLVM;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Main {
    private static String replaceExtension(String input, String extension) {
        return input.substring(0, input.lastIndexOf('/') + 1) + input.substring(input.lastIndexOf('/') + 1, input.lastIndexOf('.')) + extension;
    }
    public static void main(String[] args) throws IOException {
        Options options = new Options();

        Option inputOpt = new Option("i", "input", true, "Input file to compile");
        inputOpt.setRequired(true);
        Option outputOpt = new Option("o", "output", true, "Output file");
        Option testOpt = new Option("t", "test", false, "Compiles all files in /examples directory");
        Option runOpt = new Option("r", "run", false, "Emulates the program after compiling");
        Option rawOpt = new Option("d", "debug", true, "Writes the raw SLVM assembly to the specified file");

        options.addOption(inputOpt);
        options.addOption(outputOpt);
        options.addOption(testOpt);
        options.addOption(runOpt);
        options.addOption(rawOpt);

        CommandLine cmd = null;
        CommandLineParser parser = new BasicParser();
        HelpFormatter helper = new HelpFormatter();

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            helper.printHelp("Usage:", options);
            System.exit(0);
        }

        if (cmd.hasOption("t")) {
            for (File f : Objects.requireNonNull(new File("examples/").listFiles())) {
                if (!f.getName().endsWith(".sc"))
                    continue;
                Files.writeString(Path.of(replaceExtension(f.getAbsolutePath(), ".slvm.txt")), Compiler.compile(f.toPath()));
            }
            return;
        }

        String input = cmd.getOptionValue("i");
        String output = cmd.getOptionValue("o");

        if (output == null)
            output = replaceExtension(input, ".slvm.txt");

        String asm = Compiler.compile(Path.of(input));
        if (!Compiler.failed) {
            if (cmd.hasOption("d"))
                Files.writeString(Path.of(cmd.getOptionValue("d")), Compiler.getRawOutput());
            Files.writeString(Path.of(output), asm);
            if (cmd.hasOption("r")) {
                new SLVM(asm).run();
                System.exit(0);
            }
        }
    }
}
