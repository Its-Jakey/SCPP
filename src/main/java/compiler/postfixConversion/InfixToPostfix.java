package compiler.postfixConversion;

import antlr.SCPPParser;
import compiler.Compiler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static compiler.Compiler.appendLine;
import static compiler.Compiler.evaluateValue;

public class InfixToPostfix {
    private static int precedence(ValueOrOperatorOrID op){
        if (op.operator() == null)
            return -1;
        String x = op.operator();

        return switch (x) {
            case "^", "|", "&", "==", "!=", "<", ">", ">=", "<=" -> 2;
            case "*", "/", "%" -> 1;
            case "+", "-", "<<", ">>", "&&", "||" -> 0;
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

    public static void evaluatePostfix(List<ValueOrOperatorOrID> postfix) {

        Stack<ValueOrOperatorOrID> stack = new Stack<>();
        StringBuilder visualPostfix = new StringBuilder();

        for (ValueOrOperatorOrID post : postfix) {
            if (post.operator() != null)
                visualPostfix.append(" ").append(post.operator()).append(" ");
            else
                visualPostfix.append(" ").append(post.value().getText()).append(" ");
        }

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

                String prefix = switch (op) {
                    case "+" -> "add";
                    case "-" -> "sub";
                    case "*" -> "mul";
                    case "/" -> "div";
                    case ">>" -> "bitwiseRsf";
                    case "<<" -> "bitwiseLsf";
                    case "&" -> "bitwiseAnd";
                    case "|" -> "bitwiseOr";
                    case "&&" -> "boolAnd";
                    case "||" -> "boolOr";
                    case "==" -> "boolEqual";
                    case "!=" -> "boolNotEqual";
                    case "<" -> "smallerThan";
                    case ">" -> "largerThan";
                    case "%" -> "mod";
                    case ">=" -> "largerThanOrEqual";
                    case "<=" -> "smallerThanOrEqual";
                    default -> throw new IllegalStateException("Unexpected operator: " + op);
                };

                appendLine(prefix + "WithVar\nb");
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
