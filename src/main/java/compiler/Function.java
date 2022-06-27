package compiler;

import antlr.SCPPParser;

import java.util.LinkedHashMap;
import java.util.List;

public class Function {
    final LinkedHashMap<String, String> arguments;
    final LinkedHashMap<String, String> localVariables;
    final boolean isPublic;

    final String returnVariable;
    final String name;

    public Function(String name, List<String> arguments, boolean isPublic, String variablePrefix) {
        this.arguments = new LinkedHashMap<>();
        this.localVariables = new LinkedHashMap<>();
        this.isPublic = isPublic;
        this.name = name;
        this.returnVariable = variablePrefix + name + "_return";

        for (String arg : arguments)
            this.arguments.put(arg, variablePrefix + name + "_" + arg);
    }

    public void call(List<SCPPParser.ExpressionContext> args) {
        checkArgumentCount(args, false);

        for (int i = 0; i < Math.min(args.size(), arguments.size()); i++) {
            Compiler.evaluateExpression(args.get(i));
            Compiler.appendLine("storeAtVar\n" + arguments.values().stream().toList().get(i)); //TODO: Optimize this
        }
    }

    public void checkArgumentCount(List<SCPPParser.ExpressionContext> args, boolean failsafe) {
        if (args.size() != arguments.size()) {
            Compiler.error(name + " takes " + arguments.size() + ", but " + args.size() + " were given.");

            if (failsafe) {
                Compiler.printMessages();
                System.exit( 1);
            }
        }
    }

    public static String getID(String name, List<SCPPParser.ExpressionContext> args) {
        return name + "@" + args.size();
    }
    public static String getID(String name, int args) {
        return name + "@" + args;
    }
    public String getID() {
        return name + "@" + arguments.size();
    }
}
