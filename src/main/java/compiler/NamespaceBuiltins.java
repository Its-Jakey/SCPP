package compiler;

import antlr.SCPPParser;

import java.util.List;

public class NamespaceBuiltins {
    public static void addTo(Namespace namespace) {
        namespace.functions.put(Function.getID("pack", 0), new pack(namespace));
        namespace.functions.put(Function.getID("unpack", 1), new unpack(namespace));
    }

    private static class pack extends Function {
        private Namespace namespace;

        public pack(Namespace namespace) {
            super("", List.of(), true, namespace.name + "_");
            this.namespace = namespace;
        }

        @Override
        public void call(List<SCPPParser.ExpressionContext> args) {
            super.checkArgumentCount(args, false);

            Compiler.appendLine("imalloc\n" + namespace.variables.size());
            Compiler.appendLine("storeAtVar\npackAddress");
            Compiler.appendLine("storeAtVar\n" + super.returnVariable);
            List<Variable> variables = namespace.variables.values().stream().toList();

            for (int i = 0; i < variables.size(); i++) {
                Compiler.appendLine("loadAtVar\n" + variables.get(i).id());
                Compiler.appendLine("setValueAtPointer\npackAddress");
                Compiler.appendLine("inc\npackAddress");
            }
        }
    }

    private static class unpack extends Function {
        private Namespace namespace;

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

            for (int i = 0; i < variables.size(); i++) {
                Compiler.appendLine("getValueAtPointer\nunpackAddress");
                Compiler.appendLine("storeAtVar\n" + variables.get(i).id());
                Compiler.appendLine("inc\nunpackAddress");
            }
        }
    }
}
