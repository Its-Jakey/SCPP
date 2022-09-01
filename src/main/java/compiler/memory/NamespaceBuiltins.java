package compiler.memory;

import antlr.SCPPParser;
import compiler.Compiler;
import compiler.Evaluators;

import java.util.List;

public class NamespaceBuiltins {
    public static void addTo(Namespace namespace) {
        tryPut(namespace, new pack(namespace));
        tryPut(namespace, new unpack(namespace));
    }

    private static void tryPut(Namespace namespace, Function function) {
        String id = Function.getID(function.name, function.arguments.size());

        if (!namespace.functions.containsKey(id))
            namespace.functions.put(id, function);
    }

    private static class pack extends Function {
        private final Namespace namespace;

        public pack(Namespace namespace) {
            super("pack", List.of(), true, namespace.name + "_");
            this.namespace = namespace;
        }

        @Override
        public void call(List<SCPPParser.ExpressionContext> args) {
            super.checkArgumentCount(args, false);

            Compiler.appendLine("imalloc\n" + namespace.variables.size());
            Compiler.appendLine("storeAtVar\npackAddress");
            Compiler.appendLine("storeAtVar\n" + super.returnVariable);
            List<Variable> variables = namespace.variables.values().stream().toList();

            for (Variable variable : variables) {
                Compiler.appendLine("loadAtVar\n" + variable.id());
                Compiler.appendLine("setValueAtPointer\npackAddress");
                Compiler.appendLine("inc\npackAddress");
            }
        }
    }

    private static class unpack extends Function {
        private final Namespace namespace;

        public unpack(Namespace namespace) {
            super("unpack", List.of("data"), true, namespace.name + "_");
            this.namespace = namespace;
        }

        @Override
        public void call(List<SCPPParser.ExpressionContext> args) {
            super.checkArgumentCount(args, true);
            List<Variable> variables = namespace.variables.values().stream().toList();

            Evaluators.evaluateExpression(args.get(0));
            Compiler.appendLine("storeAtVar\nunpackAddress");

            for (Variable variable : variables) {
                Compiler.appendLine("getValueAtPointer\nunpackAddress");
                Compiler.appendLine("storeAtVar\n" + variable.id());
                Compiler.appendLine("inc\nunpackAddress");
            }
        }
    }
}
