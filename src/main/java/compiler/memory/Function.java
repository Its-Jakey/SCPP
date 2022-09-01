package compiler.memory;

import antlr.SCPPParser;
import compiler.Compiler;
import compiler.Evaluators;

import java.util.LinkedHashMap;
import java.util.List;

public class Function {
    public final LinkedHashMap<String, String> arguments;
    public final LinkedHashMap<String, String> localVariables;
    public final boolean isPublic;

    public final String returnVariable;
    public final String name;
    private final String variablePrefix;
    public SCPPParser.FunctionDeclarationContext context = null;
    public Program program = null;
    public boolean inline = false;
    public int level = 0;
    public final List<String> rawArgs;

    public Function(String name, List<String> arguments, boolean isPublic, String variablePrefix) {
        this.arguments = new LinkedHashMap<>();
        this.localVariables = new LinkedHashMap<>();
        this.isPublic = isPublic;
        this.name = name;
        this.variablePrefix = variablePrefix;
        this.returnVariable = variablePrefix + name + "_return";
        this.rawArgs = arguments;

        for (String arg : arguments)
            this.arguments.put(arg, variablePrefix + name + "_" + arg);
    }

    private Function(Function source, boolean isPublic) {
        this.arguments = source.arguments;
        this.localVariables = source.localVariables;
        this.isPublic = isPublic;
        this.returnVariable = source.returnVariable;
        this.name = source.name;
        this.variablePrefix = source.variablePrefix;
        this.context = source.context;
        this.program = source.program;
        this.inline = source.inline;
        this.level = source.level;
        this.rawArgs = source.rawArgs;
    }

    public String getLabel() {
        return variablePrefix + getID();
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
            Compiler.appendLine("jts\n%" + getLabel() + "%");
    }

    public void checkArgumentCount(List<SCPPParser.ExpressionContext> args, boolean failsafe) {
        if (args.size() != arguments.size()) {
            Compiler.error(name + " takes " + arguments.size() + " arguments, but " + args.size() + " were given.");

            if (failsafe) {
                Compiler.printMessages();
                System.exit( 1);
            }
        }
    }

    public static Function changeVisibility(Function function, boolean isPublic) {
        return new Function(function, isPublic);
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
