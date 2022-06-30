package compiler;

import antlr.SCPPParser;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Function {
    final LinkedHashMap<String, String> arguments;
    final LinkedHashMap<String, String> localVariables;
    final boolean isPublic;

    final String returnVariable;
    final String name;
    private final String variablePrefix;
    SCPPParser.FunctionDeclarationContext context = null;
    Program program = null;
    boolean inline = false;

    public Function(String name, List<String> arguments, boolean isPublic, String variablePrefix) {
        this.arguments = new LinkedHashMap<>();
        this.localVariables = new LinkedHashMap<>();
        this.isPublic = isPublic;
        this.name = name;
        this.variablePrefix = variablePrefix;
        this.returnVariable = variablePrefix + name + "_return";

        for (String arg : arguments)
            this.arguments.put(arg, variablePrefix + name + "_" + arg);
    }

    public void call(List<SCPPParser.ExpressionContext> args) {
        checkArgumentCount(args, false);

        List<String> argumentList = arguments.values().stream().toList();

        for (int i = 0; i < Math.min(args.size(), arguments.size()); i++) {
            Evaluators.evaluateExpression(args.get(i));
            Compiler.appendLine("storeAtVar\n" + argumentList.get(i));
        }
        if (inline) {
            Program programBackup = Compiler.currentProgram.clone();
            Compiler.currentProgram.currentFunction = this;

            Compiler.compileContext(context.codeBlock());
            Compiler.currentProgram = programBackup;
        } else
            Compiler.appendLine("jts\n%" + variablePrefix + name + "%");
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
