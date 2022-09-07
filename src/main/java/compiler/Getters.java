package compiler;

import antlr.SCPPParser;
import compiler.memory.Function;
import compiler.memory.Namespace;
import compiler.memory.Variable;

import java.util.List;
import java.util.Objects;

import static compiler.Compiler.*;

public class Getters {
    public static Namespace getNamespace(String name) {
        if (currentProgram.namespaces.get(name) == null) {
            errorAndKill("Unknown namespace '" + name + "'");
            Compiler.printMessages();
        }
        return currentProgram.namespaces.get(name);
    }

    public static String last = "";

    public static boolean hasConstant(String name) {
        last = currentProgram.fileName + "@" + name;
        return constants.containsKey(currentProgram.fileName + "@" + name);
    }

    public static String getConstant(String name) {
        return constants.get(currentProgram.fileName + "@" + name);
    }

    public static void addConstant(String name, String value) {
        if (hasConstant(name))
            errorAndKill("Duplicate constant '" + name + "'");
        constants.put(currentProgram.fileName + "@" + name, value);
    }

    public static Variable getVariable(Namespace namespace, Function function, String name) {
        if (namespace == null)
            errorAndKill("Variable '" + name + "' was requested outside of namespace.");

        if (function == null || !(function.arguments.containsKey(name) || function.localVariables.containsKey(name))) {
            if (Objects.requireNonNull(namespace).variables.containsKey(name) && (namespace.equals(currentProgram.currentNamespace) || namespace.variables.get(name).isPublic()))
                return namespace.variables.get(name);
            if (!hasConstant(name))
                errorAndKill("Unknown variable '" + name + "'");
            appendLine("ldi\n" + getConstant(name));
            appendLine("storeAtVar\nconstantValue");
            return new Variable("constantValue", false);
        }
        if (function.arguments.containsKey(name))
            return new Variable(function.arguments.get(name), false);
        return new Variable(function.localVariables.get(name), false);
    }

    public static Function getFunction(Namespace namespace, String name, List<SCPPParser.ExpressionContext> args) {
        if (namespace == null)
            errorAndKill("Function '" + name + "' was requested outside of namespace");
        if (builtins.functions.containsKey(Function.getID(name, args)))
            return builtins.functions.get(Function.getID(name, args));
        if (!Objects.requireNonNull(namespace).functions.containsKey(Function.getID(name, args)) || (!namespace.equals(currentProgram.currentNamespace) && !namespace.functions.get(Function.getID(name, args)).isPublic))
            errorAndKill("Unknown function '" + name + "'");
        return namespace.functions.get(Function.getID(name, args));
    }

    public static Function getFunction(SCPPParser.FunctionCallContext ctx) {
        List<SCPPParser.ExpressionContext> args = Evaluators.evaluateArgumentArray(ctx.argumentArray());
        SCPPParser.VariableContext variable = ctx.variable();
        Function ret = null;

        if (variable.variable() != null)
            ret = getFunction(getNamespace(variable.ID().getText()), variable.variable().ID().getText(), args);
        else if (builtins.functions.containsKey(Function.getID(variable.ID().getText(), args)))
            ret = builtins.functions.get(Function.getID(variable.ID().getText(), args));
        else if (currentProgram.currentFunction != null && currentProgram.currentFunction.getID().equals(Function.getID(variable.ID().getText(), args)))
            ret = currentProgram.currentFunction;
        else if (!currentProgram.currentNamespace.functions.containsKey(Function.getID(variable.ID().getText(), args))) {
            errorAndKill("Unknown function '" + variable.ID().getText() + "'");
        }
        else
            ret = currentProgram.currentNamespace.functions.get(Function.getID(variable.ID().getText(), args));
        return ret;
    }
}
