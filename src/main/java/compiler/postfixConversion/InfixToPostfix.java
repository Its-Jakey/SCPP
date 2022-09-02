package compiler.postfixConversion;

import antlr.SCPPParser;
import compiler.Compiler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static compiler.Compiler.appendLine;
import static compiler.Evaluators.evaluateValue;
import static compiler.Evaluators.pushValueToStack;

public class InfixToPostfix {
    private static int precedence(ValueOrOperatorOrID op){
        if (op.operator() == null)
            return -1;
        String x = op.operator();

        return switch (x) {
            case "+", "-" -> 1;
            case "..", "*", "/", "%" -> 2;
            case "&", "|", "^" -> 3;
            case "<", ">", "<=", ">=", "==", "!=", "<<", ">>" -> 4;
            case "&&", "||" -> 5;
            default -> -1;
        };
    }

    public static List<ValueOrOperatorOrID> infixToPostfix(List<ValueOrOperatorOrID> input){
        Stack<ValueOrOperatorOrID> stk = new Stack<>();
        List<ValueOrOperatorOrID> ret = new ArrayList<>();

        for (ValueOrOperatorOrID x : input) {
            if (x.value() == null && x.operator() == null)
                continue;

            if (x.value() != null)
                ret.add(x);
            else if (x.operator().equals("("))
                stk.push(x);
            else if (x.operator().equals(")")) {

                while (!stk.isEmpty() && stk.peek().operator() != null && !stk.peek().operator().equals("("))
                    ret.add(stk.pop());
                if (!stk.isEmpty())
                    stk.pop();
            } else {
                while (!stk.isEmpty() && precedence(stk.peek()) >= precedence(x))
                    ret.add(stk.pop());
                stk.push(x);
            }
        }
        while(!stk.isEmpty())
            ret.add(stk.pop());
        return ret;
    }

    private static void evaluatePostfixOnStack(List<ValueOrOperatorOrID> postfix) {
        for (ValueOrOperatorOrID x : postfix) {
            if (x.value() != null) {
                pushValueToStack(x.value());
            } else
                appendLine("stack" + Compiler.getOperatorSurname(x.operator()).toUpperCase().charAt(0) + Compiler.getOperatorSurname(x.operator()).substring(1));
        }
        appendLine("stackPopA");
    }

    private static void evaluatePostfixWithVars(List<ValueOrOperatorOrID> postfix) {
        Stack<ValueOrOperatorOrID> stack = new Stack<>();

        for (ValueOrOperatorOrID x : postfix) {
            if (x.value() != null || x.getId() != null) {
                stack.push(x);
            } else {
                ValueOrOperatorOrID b = stack.pop();
                ValueOrOperatorOrID a = stack.pop();
                String op = x.operator();

                if (a.value() == null)
                    appendLine("loadAtVar\n" + a.getId());
                else
                    evaluateValue(a.value());

                appendLine("storeAtVar\na");
                if (b.value() == null)
                    appendLine("loadAtVar\n" + b.getId());
                else
                    evaluateValue(b.value());
                appendLine("storeAtVar\nb");
                appendLine("loadAtVar\na");

                String prefix = Compiler.getOperatorInstruction(op);

                appendLine(prefix + "\nb");
                appendLine("storeAtVar\ntmp" + Compiler.createTemp());
                ValueOrOperatorOrID tmp = new ValueOrOperatorOrID(null, null);
                tmp.setId("tmp" + Compiler.endTemp());
                stack.push(tmp);
            }
        }
        ValueOrOperatorOrID lastValue = stack.pop();

        if (lastValue.value() != null)
            evaluateValue(lastValue.value());
        else
            appendLine("loadAtVar\n" + lastValue.getId());

        if (!stack.isEmpty())
            throw new RuntimeException("Postfix was not evaluated correctly, stack still has " + stack.size() + " items.");
    }

    public static void evaluatePostfix(List<ValueOrOperatorOrID> postfix) {
        evaluatePostfixOnStack(postfix);
        //evaluatePostfixWithVars(postfix);
    }

    public static void addExpressionToList(SCPPParser.ExpressionContext expression, List<ValueOrOperatorOrID> ret) {
        if (expression.OPERATOR() != null) {
            addExpressionToList(expression.expression(0), ret);
            ret.add(new ValueOrOperatorOrID(null, expression.OPERATOR().getText()));
            addExpressionToList(expression.expression(1), ret);
        } else if (expression.value() != null)
            ret.add(new ValueOrOperatorOrID(expression.value(), null));
        else {
            ret.add(new ValueOrOperatorOrID(null, "("));
            addExpressionToList(expression.expression(0), ret);
            ret.add(new ValueOrOperatorOrID(null, ")"));
        }
    }
}
