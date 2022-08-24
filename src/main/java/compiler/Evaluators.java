package compiler;

import antlr.SCPPParser;
import compiler.postfixConversion.InfixToPostfix;
import compiler.postfixConversion.ValueOrOperatorOrID;
import org.apache.commons.text.StringEscapeUtils;

import java.awt.image.BufferedImage;
import java.beans.Expression;
import java.util.ArrayList;
import java.util.List;

import static compiler.Compiler.*;
import static compiler.Getters.*;

public class Evaluators {
    static List<SCPPParser.ExpressionContext> evaluateArgumentArray(SCPPParser.ArgumentArrayContext ctx) {
        List<SCPPParser.ExpressionContext> ret = new ArrayList<>();
        SCPPParser.ArgumentArrayContext cur = ctx;

        while (cur != null) {
            ret.add(cur.expression());
            cur = cur.argumentArray();
        }
        return ret;
    }

    static void loadValueAtArrayIndex(String base, SCPPParser.ArrayIndexContext arrayIndex) {
        getValueAtIndex(base, arrayIndex, createTemp());
        endTemp();
    }

    static void setValueAtArrayIndex(String base, SCPPParser.ArrayIndexContext arrayIndex, SCPPParser.ExpressionContext value) {
        getValueAtIndex(base, arrayIndex, createTemp());
        evaluateExpression(value);
        appendLine("setValueAtPointer\n" + endTemp());
    }

    static void getValueAtIndex(String array, SCPPParser.ArrayIndexContext index, String indexName) {
        if (index == null) return;

        evaluateExpression(index.expression());
        appendLine("addWithVar\n" + array);
        appendLine("storeAtVar\n" + indexName);
        appendLine("getValueAtPointer\n" + indexName);

        if (index.arrayIndex() != null)
            appendLine("storeAtVar\n" + indexName);
        getValueAtIndex(indexName, index.arrayIndex(), indexName);
    }


    static void assignArgumentArrayToArray(SCPPParser.ArgumentArrayContext ctx) {
        List<SCPPParser.ExpressionContext> args = evaluateArgumentArray(ctx);
        String address = createTemp();

        appendLine("imalloc\n" + args.size());
        appendLine("storeAtVar\n" + address);
        appendLine("storeAtVar\n" + createTemp());

        for (int i = 0; i < args.size(); i++) {
            SCPPParser.ExpressionContext arg = args.get(i);
            evaluateExpression(arg);

            appendLine("setValueAtPointer\n" + address);
            if (i + 1 < args.size())
                appendLine("inc\n" + address);
        }

        appendLine("loadAtVar\n" + endTemp());
        endTemp();
    }

    public static void evaluateValue(SCPPParser.ValueContext ctx) {
        if (ctx.variable() != null || ctx.functionCall() != null) {
            String varName = null;

            if (ctx.variable() != null) {
                SCPPParser.VariableContext variable = ctx.variable();

                if (variable.variable() != null)
                    varName = getVariable(getNamespace(variable.ID().getText()), null, variable.variable().ID().getText()).id();
                else
                    varName = getVariable(currentProgram.currentNamespace, currentProgram.currentFunction, variable.ID().getText()).id();
            }
            else if (ctx.functionCall() != null)
                varName = evaluateFunctionCall(ctx.functionCall()).returnVariable;
            if (ctx.arrayIndex() != null)
                loadValueAtArrayIndex(varName, ctx.arrayIndex());
            else
                appendLine("loadAtVar\n" + varName);
            return;
        }
        if (ctx.STRING() != null)
            appendLine("ldi\n" + ctx.STRING().getText().substring(1, ctx.STRING().getText().length() - 1));
        else if (ctx.INT() != null)
            appendLine("ldi\n" + ctx.INT().getText());
        else if (ctx.HEX() != null)
            appendLine("ldi\n" + Long.parseLong(ctx.HEX().getText().substring(2), 16));
        else if (ctx.BIN() != null)
            appendLine("ldi\n" + Long.parseLong(ctx.BIN().getText().substring(2), 2));
        else if (ctx.argumentArray() != null)
            assignArgumentArrayToArray(ctx.argumentArray());
        else {
            SCPPParser.ConditionalValueContext val = ctx.conditionalValue();

            evaluateExpression(val.expression(1));
            appendLine("storeAtVar\nconditionalTrue");
            evaluateExpression(val.expression(2));
            appendLine("storeAtVar\nconditionalFalse");
            evaluateExpression(val.expression(0));
            appendLine("conditionalValueSet\nconditionalTrue\nconditionalFalse");
        }
    }

    static void evaluateExpression(SCPPParser.ExpressionContext ctx) {
        if (ctx.value() != null) {
            evaluateValue(ctx.value());
            return;
        }
        List<ValueOrOperatorOrID> postfix = new ArrayList<>();
        InfixToPostfix.addExpressionToList(ctx, postfix);
        InfixToPostfix.evaluatePostfix(InfixToPostfix.infixToPostfix(postfix));
    }

    private static String varToString(SCPPParser.VariableContext ctx) {
        SCPPParser.VariableContext cur = ctx;
        StringBuilder ret = new StringBuilder();

        while (cur != null) {
            ret.append("::").append(cur.ID().getText());
            cur = cur.variable();
        }
        return ret.substring(2);
    }

    static Function evaluateFunctionCall(SCPPParser.FunctionCallContext ctx) {
        List<SCPPParser.ExpressionContext> args = evaluateArgumentArray(ctx.argumentArray());
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
        if (ret == null)
            Compiler.printMessages();
        ret.call(args);
        return ret;
    }

    static List<String> evaluateFunctionArgumentArray(SCPPParser.FunctionArgumentArrayContext ctx) {
        SCPPParser.FunctionArgumentArrayContext cur = ctx;
        List<String> ret = new ArrayList<>();

        while (cur != null) {
            ret.add(cur.ID().getText());
            cur = cur.functionArgumentArray();
        }
        return ret;
    }
}
