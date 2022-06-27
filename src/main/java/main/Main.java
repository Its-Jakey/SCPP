package main;

import compiler.Compiler;
import org.apache.commons.cli.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Main {
    private static String getOutputPathFromInput(String input) {
        return input.substring(0, input.lastIndexOf('/') + 1) + input.substring(input.lastIndexOf('/') + 1, input.lastIndexOf('.')) + ".txt";
    }
    public static void main(String[] args) throws Exception {
        Options options = new Options();

        Option inputOpt = new Option("i", "input", true, "Input file to compile");
        inputOpt.setRequired(true);
        Option outputOpt = new Option("o", "output", true, "Output file");
        Option testOpt = new Option("t", "test", false, "Compiles all files in /examples directory");

        options.addOption(inputOpt);
        options.addOption(outputOpt);
        options.addOption(testOpt);

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
                Files.writeString(Path.of(getOutputPathFromInput(f.getAbsolutePath())), Compiler.compile(f.toPath()));
            }
            return;
        }

        String input = cmd.getOptionValue("i");
        String output = cmd.getOptionValue("o");

        if (output == null)
            output = getOutputPathFromInput(input);

        String asm = Compiler.compile(Path.of(input));
        Files.writeString(Path.of(output), asm);
    }
}
