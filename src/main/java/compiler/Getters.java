package compiler;

import antlr.SCPPParser;

import java.util.List;

import static compiler.Compiler.*;

public class Getters {
    static Namespace getNamespace(String name) {
        if (!currentProgram.namespaces.containsKey(name))
            errorAndKill("Unknown namespace '" + name + "'");
        return currentProgram.namespaces.get(name);
    }

    static String last = "";

    static boolean hasConstant(String name) {
        last = currentProgram.fileName + "@" + name;
        return constants.containsKey(currentProgram.fileName + "@" + name);
    }

    static String getConstant(String name) {
        return constants.get(currentProgram.fileName + "@" + name);
    }

    static void addConstant(String name, String value) {
        if (hasConstant(name))
            errorAndKill("Duplicate constant '" + name + "'");
        constants.put(currentProgram.fileName + "@" + name, value);
    }

    static Variable getVariable(Namespace namespace, Function function, String name) {
        if (namespace == null)
            errorAndKill("Variable '" + name + "' was requested outside of namespace.");

        if (function == null || !(function.arguments.containsKey(name) || function.localVariables.containsKey(name))) {
            if (namespace.variables.containsKey(name) && (namespace.equals(currentProgram.currentNamespace) || namespace.variables.get(name).isPublic()))
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

    static Function getFunction(Namespace namespace, String name, List<SCPPParser.ExpressionContext> args) {
        if (namespace == null)
            errorAndKill("Function '" + name + "' was requested outside of namespace");
        if (builtins.functions.containsKey(Function.getID(name, args)))
            return builtins.functions.get(Function.getID(name, args));
        if (!namespace.functions.containsKey(Function.getID(name, args)) || (!namespace.equals(currentProgram.currentNamespace) && !namespace.functions.get(Function.getID(name, args)).isPublic))
            errorAndKill("Unknown function '" + name + "'");
        return namespace.functions.get(Function.getID(name, args));
    }
}
