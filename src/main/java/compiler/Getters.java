package compiler;

import antlr.SCPPParser;

import java.util.List;

import static compiler.Compiler.*;

public class Getters {
    static Namespace getNamespace(String name) {
        if (!namespaces.containsKey(name))
            errorAndKill("Unknown namespace '" + name + "'");
        return namespaces.get(name);
    }

    static String last = "";

    static boolean hasConstant(String name) {
        last = fileName + "@" + name;
        return constants.containsKey(fileName + "@" + name);
    }

    static String getConstant(String name) {
        return constants.get(fileName + "@" + name);
    }

    static void addConstant(String name, String value) {
        if (hasConstant(name))
            errorAndKill("Duplicate constant '" + name + "'");
        constants.put(fileName + "@" + name, value);
    }

    static Variable getVariable(Namespace namespace, Function function, String name) {
        if (namespace == null)
            errorAndKill("Variable '" + name + "' was requested outside of namespace.");

        if (function == null || !(function.arguments.containsKey(name) || function.localVariables.containsKey(name))) {
            if (namespace.variables.containsKey(name))
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
        if (!namespace.functions.containsKey(Function.getID(name, args)))
            errorAndKill("Unknown function '" + name + "'");
        return namespace.functions.get(Function.getID(name, args));
    }
}
