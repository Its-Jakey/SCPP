package compiler;

import antlr.SCPPParser;
import com.sun.jdi.Method;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import java.util.List;

import static compiler.Compiler.*;
import static compiler.Evaluators.evaluateExpression;

public class Builtins extends Namespace {
    public Builtins() {
        super("__builtins__", true);
        addAllArgs(new println());
        addAllArgs(new print());
        addAllArgs(new _asm_());
        add(new malloc());
        add(new exit());
        addAllArgs(new concat());
    }

    private void addAllArgs(Function function) {
        for (int i = 0; i < 25; i++)
            super.functions.put(Function.getID(function.name, i), function);
    }
    private void add(Function function) {
        super.functions.put(function.getID(), function);
    }

    private static class println extends Function {
        public println() {
            super("println", List.of(), true, "__builtins__");
        }

        @Override
        public void call(List<SCPPParser.ExpressionContext> args) {
            for (int i = 0; i < args.size() - 1; i++) {
                evaluateExpression(args.get(i));
                appendLine("print");

                if (i + 1 < args.size()) {
                    appendLine("ldi\n ");
                    appendLine("print");
                }
            }
            evaluateExpression(args.get(args.size() - 1));
            appendLine("println");
        }
    }

    private static class print extends Function {
        public print() {
            super("print", List.of(), true, "__builtins__");
        }

        @Override
        public void call(List<SCPPParser.ExpressionContext> args) {
            for (int i = 0; i < args.size(); i++) {
                SCPPParser.ExpressionContext arg = args.get(i);
                evaluateExpression(arg);
                appendLine("print");

                if (i + 1 < args.size()) {
                    appendLine("ldi\n ");
                    appendLine("print");
                }
            }
        }
    }

    private static class _asm_ extends Function {
        public _asm_() {
            super("_asm_", List.of(), true, "__builtins__");
        }

        @Override
        public void call(List<SCPPParser.ExpressionContext> args) {
            if (args.size() < 1) {
                error("_asm_ takes at least 1 argument, but 0 were given");
                return;
            }
            String command = args.get(0).getText();

            StringBuilder asm = new StringBuilder(StringEscapeUtils.escapeJava(command.substring(1, command.length() - 1)));
            for (int i = 1; i < args.size(); i++) {
                evaluateExpression(args.get(i));
                appendLine("storeAtVar\nasmExpression" + i);

                asm.append("\nasmExpression").append(i);
            }

            appendLine(asm);
            appendLine("storeAtVar\n" + super.returnVariable);
        }
    }

    private static class malloc extends Function {


        public malloc() {
            super("malloc", List.of("words"), true, "__builtins__");
        }
        @Override
        public void call(List<SCPPParser.ExpressionContext> args) {
            super.checkArgumentCount(args, false);

            Evaluators.evaluateExpression(args.get(0));
            Compiler.appendLine("storeAtVar\nmallocWords");
            Compiler.appendLine("malloc\nmallocWords");
            Compiler.appendLine("storeAtVar\n" + super.returnVariable);
        }
    }

    private static class exit extends Function {

        public exit() {
            super("exit", List.of(), true, "__builtins__");
        }

        @Override
        public void call(List<SCPPParser.ExpressionContext> args) {
            super.checkArgumentCount(args, false);
            appendLine("done");
        }
    }

    private static class concat extends Function {

        public concat() {
            super("concat", List.of(), true, "__builtins__");
        }

        @Override
        public void call(List<SCPPParser.ExpressionContext> args) {
            if (args.size() < 2)
                error("concat takes at least 2 arguments, but " + args.size() + " were given.");
            appendLine("ldi\n");
            appendLine("storeAtVar\n" + super.returnVariable);

            for (SCPPParser.ExpressionContext arg : args) {
                evaluateExpression(arg);
                appendLine("storeAtVar\nconcatTmp");
                appendLine("loadAtVar\n" + super.returnVariable);
                appendLine("join\nconcatTmp");
                appendLine("storeAtVar\n" + super.returnVariable);
            }
        }
    }
}
