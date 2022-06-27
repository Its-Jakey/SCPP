package compiler;

import antlr.SCPPLexer;
import antlr.SCPPListener;
import antlr.SCPPParser;
import compiler.postfixConversion.InfixToPostfix;
import compiler.postfixConversion.ValueOrOperatorOrID;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.text.StringEscapeUtils;

import java.nio.file.Path;
import java.util.*;

public class Compiler implements SCPPListener {
    private static StringBuilder output;
    private static boolean failed;
    private static String fileName = null;
    private static int row, col;
    private static List<String> messages;
    private static LinkedHashMap<String, Namespace> namespaces;
    private static Namespace currentNamespace;
    private static Function currentFunction;
    private static Stack<Integer> tempStack;
    private static int tempN = 0;
    private static Builtins builtins = new Builtins();

    public static String getPrefixMessage() {
        return fileName + " " + row + ":" + col;
    }
    public static void error(String msg) {
        messages.add(getPrefixMessage() + " Error: " + msg);
        failed = true;
    }
    public static void errorAndKill(String msg) {
        printMessages();
        throw new RuntimeException(getPrefixMessage() + "\t" + msg);
    }
    public static void warn(String msg) {
        messages.add(getPrefixMessage() + " Warning: " + msg);
    }

    private String getVariableName(SCPPParser.VariableContext ctx) {
        return "";
    }

    public static void printMessages() {
        messages.forEach(System.out::println);
    }

    private static void runWalker(CharStream stream) throws Exception{
        SCPPLexer lexer = new SCPPLexer(stream);
        SCPPParser parser = new SCPPParser(new CommonTokenStream(lexer));
        ParseTreeWalker walker = new ParseTreeWalker();

        walker.walk(new Compiler(), parser.program());
    }

    public static void compile(Path file){
        long startTime = System.currentTimeMillis();

        init();
        fileName = file.getFileName().toString();

        try {
            runWalker(CharStreams.fromPath(file));
            printMessages();

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            failed = true;
        }
        end();
        long time = System.currentTimeMillis() - startTime;

        if (failed)
            System.err.println("Build failed in " + time / 1000d + " seconds");
        else
            System.out.println("Build succeeded in " + time / 1000d + " seconds");
    }

    private static void init() {
        output = new StringBuilder();
        failed = false;
        messages = new ArrayList<>();
        currentNamespace = null;
        currentFunction = null;
        namespaces = new LinkedHashMap<>();
        tempStack = new Stack<>();
        tempN = 0;

        appendLine("ldi\n1\nstoreAtVar\nONE");
        appendLine("jmp\n%ENTRY%");
    }
    private static void end() {
        appendLine(":ENTRY");
        Namespace mainNamespace = null;

        for (Namespace namespace : namespaces.values()) {
            appendLine("jts\n%" + namespace.name + "%");

            if (namespace.functions.containsKey(Function.getID("main", 0)) && namespace.functions.get(Function.getID("main", 0)).isPublic)
                mainNamespace = namespace;
        }
        if (mainNamespace == null)
            error("main function not found, try defining one with 'public func main(){}'");
        else
            appendLine("jts\n%" + mainNamespace.name + "_main%\ndone");
    }

    public static void appendLine(Object line) {
        output.append("\n").append(line);
    }

    public static String getOutput(){
        if (output.length() == 0)
            return "";
        return Assembler.assemble(output.substring(1));
    }

    public static String getRawOutput(){
        if (output.length() == 0)
            return "";
        return output.substring(1);
    }

    public static String createTemp() {
        String ret = "temp" + tempN;
        tempStack.push(tempN);
        tempN++;
        return ret;
    }

    public static String endTemp() {
        return "temp" + tempStack.pop();
    }

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
        SCPPParser.ArrayIndexContext cur = arrayIndex;
        String arrayStart = createTemp();

        appendLine("loadAtVar\n" + base);
        appendLine("storeAtVar\n" + arrayStart);

        while (cur != null) {
            evaluateExpression(cur.expression());
            appendLine("addWithVar\n" + arrayStart);
            appendLine("storeAtVar\ntempArrayIndex");
            appendLine("getValueAtPointer\ntempArrayIndex");

            cur = cur.arrayIndex();
            if (cur != null)
                appendLine("storeAtVar\ntempArrayStart");
        }
        endTemp();
    }

    static void assignArgumentArrayToArray(SCPPParser.ArgumentArrayContext ctx) {
        List<SCPPParser.ExpressionContext> args = evaluateArgumentArray(ctx);
        String address = createTemp();

        appendLine("imalloc\n" + args.size());
        appendLine("storeAtVar\n" + address);

        for (SCPPParser.ExpressionContext arg : args) {
            evaluateExpression(arg);

            appendLine("setValueAtPointer\n" + address);
            appendLine("loadAtVar\n" + address);
            appendLine("addWithVar\nONE");
            appendLine("storeAtVar\n" + address);
        }
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
                    varName = getVariable(currentNamespace, currentFunction, variable.ID().getText()).id();
            }
            else if (ctx.functionCall() != null) {
                varName = evaluateFunctionCall(ctx.functionCall()).returnVariable;

                if (ctx.arrayIndex() != null)
                    loadValueAtArrayIndex(varName, ctx.arrayIndex());
                else
                    appendLine("loadAtVar\n" + varName);
            }
            if (ctx.arrayIndex() != null)
                loadValueAtArrayIndex(varName, ctx.arrayIndex());
            else
                appendLine("loadAtVar\n" + varName);
            return;
        }
        if (ctx.STRING() != null)
            appendLine("ldi\n" + StringEscapeUtils.escapeJava(ctx.STRING().getText().substring(1, ctx.STRING().getText().length() - 1)));
        else if (ctx.INT() != null)
            appendLine("ldi\n" + ctx.INT().getText());
        else if (ctx.HEX() != null)
            appendLine("ldi\n" + Integer.parseInt(ctx.HEX().getText(), 16));
        else if (ctx.BIN() != null)
            appendLine("ldi\n" + Integer.parseInt(ctx.BIN().getText(), 2));
        else
            assignArgumentArrayToArray(ctx.argumentArray());
    }

    static void evaluateExpression(SCPPParser.ExpressionContext ctx) {
        List<ValueOrOperatorOrID> postfix = new ArrayList<>();
        InfixToPostfix.addExpressionToList(ctx, postfix);
        InfixToPostfix.evaluatePostfix(InfixToPostfix.infixToPostfix(postfix));
    }

    static Function evaluateFunctionCall(SCPPParser.FunctionCallContext ctx) {
        List<SCPPParser.ExpressionContext> args = evaluateArgumentArray(ctx.argumentArray());
        SCPPParser.VariableContext variable = ctx.variable();
        Function ret = null;

        if (variable.variable() != null)
            ret = getFunction(getNamespace(variable.ID().getText()), variable.variable().ID().getText(), args);
        else if (builtins.functions.containsKey(Function.getID(variable.ID().getText(), args)))
            ret = builtins.functions.get(Function.getID(variable.ID().getText(), args));
        else if (currentFunction != null && currentFunction.name.equals(variable.ID().getText()))
            ret = currentFunction;
        else if (!currentNamespace.functions.containsKey(Function.getID(variable.ID().getText(), args))) {
            errorAndKill("Unknown function '" + variable.ID().getText() + "'");
        }
        else
            ret = currentNamespace.functions.get(Function.getID(variable.ID().getText(), args));
        ret.call(args);
        return ret;
    }

    static Namespace getNamespace(String name) {
        if (!namespaces.containsKey(name))
            errorAndKill("Unknown namespace '" + name + "'");
        return namespaces.get(name);
    }

    static Variable getVariable(Namespace namespace, Function function, String name) {
        if (namespace == null)
            errorAndKill("Variable '" + name + "' was requested outside of namespace.");

        if (function == null && !(function.arguments.containsKey(name) || !function.localVariables.containsKey(name))) {
            if (!namespace.variables.containsKey(name))
                errorAndKill("Unknown variable '" + name + "'");
            return namespace.variables.get(name);
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

    static List<String> evaluateFunctionArgumentArray(SCPPParser.FunctionArgumentArrayContext ctx) {
        SCPPParser.FunctionArgumentArrayContext cur = ctx;
        List<String> ret = new ArrayList<>();

        while (cur != null) {
            ret.add(cur.ID().getText());
            cur = cur.functionArgumentArray();
        }
        return ret;
    }


    @Override
    public void enterProgram(SCPPParser.ProgramContext ctx) {

    }

    @Override
    public void exitProgram(SCPPParser.ProgramContext ctx) {

    }

    @Override
    public void enterStatement(SCPPParser.StatementContext ctx) {

    }

    @Override
    public void exitStatement(SCPPParser.StatementContext ctx) {

    }

    @Override
    public void enterBracketStatement(SCPPParser.BracketStatementContext ctx) {

    }

    @Override
    public void exitBracketStatement(SCPPParser.BracketStatementContext ctx) {

    }

    @Override
    public void enterNamespaceDeclaration(SCPPParser.NamespaceDeclarationContext ctx) {
        if (currentNamespace != null)
            errorAndKill("Cannot declare namespace from inside of namespace");
        if (namespaces.containsKey(ctx.ID().getText()))
            errorAndKill("Duplicate namespace '" + ctx.ID().getText() + "'");
        currentNamespace = new Namespace(ctx.ID().getText(), ctx.pub != null);
        appendLine(":" + currentNamespace.name);
    }

    @Override
    public void exitNamespaceDeclaration(SCPPParser.NamespaceDeclarationContext ctx) {
        namespaces.put(currentNamespace.name, currentNamespace);
        currentNamespace = null;
        appendLine("ret");
    }

    @Override
    public void enterFunctionDeclaration(SCPPParser.FunctionDeclarationContext ctx) {
        if (currentFunction != null)
            errorAndKill("Cannot define function from inside of function");
        if (currentNamespace == null)
            errorAndKill("Cannot define function from outside of namespace");
        List<String> args = evaluateFunctionArgumentArray(ctx.functionArgumentArray());
        String name = ctx.ID().getText();

        if (currentNamespace.functions.containsKey(Function.getID(name, args.size())) || builtins.functions.containsKey(Function.getID(name, args.size())))
            errorAndKill("Duplicate function '" + name);
        currentFunction = new Function(name, args, ctx.pub != null, currentNamespace.name + "_");

        appendLine("jmp\n%" + currentNamespace.name + "_" + name + "_end%");
        appendLine(":" + currentNamespace.name + "_" + name);
    }

    @Override
    public void exitFunctionDeclaration(SCPPParser.FunctionDeclarationContext ctx) {
        currentNamespace.functions.put(currentFunction.getID(), currentFunction);
        appendLine("ret");
        appendLine(":" + currentNamespace.name + "_" + currentFunction.name + "_end");
        currentFunction = null;
    }

    @Override
    public void enterIfStatement(SCPPParser.IfStatementContext ctx) {

    }

    @Override
    public void exitIfStatement(SCPPParser.IfStatementContext ctx) {

    }

    @Override
    public void enterWhileLoop(SCPPParser.WhileLoopContext ctx) {

    }

    @Override
    public void exitWhileLoop(SCPPParser.WhileLoopContext ctx) {

    }

    @Override
    public void enterForLoop(SCPPParser.ForLoopContext ctx) {

    }

    @Override
    public void exitForLoop(SCPPParser.ForLoopContext ctx) {

    }

    @Override
    public void enterNonBracketStatement(SCPPParser.NonBracketStatementContext ctx) {

    }

    @Override
    public void exitNonBracketStatement(SCPPParser.NonBracketStatementContext ctx) {

    }

    @Override
    public void enterVariableDeclaration(SCPPParser.VariableDeclarationContext ctx) {

    }

    @Override
    public void exitVariableDeclaration(SCPPParser.VariableDeclarationContext ctx) {

    }

    @Override
    public void enterVariableValueChange(SCPPParser.VariableValueChangeContext ctx) {

    }

    @Override
    public void exitVariableValueChange(SCPPParser.VariableValueChangeContext ctx) {

    }

    @Override
    public void enterFunctionCall(SCPPParser.FunctionCallContext ctx) {

    }

    @Override
    public void exitFunctionCall(SCPPParser.FunctionCallContext ctx) {
        if (!(ctx.parent instanceof SCPPParser.ValueContext))
            evaluateFunctionCall(ctx);
    }

    @Override
    public void enterReturnStatement(SCPPParser.ReturnStatementContext ctx) {

    }

    @Override
    public void exitReturnStatement(SCPPParser.ReturnStatementContext ctx) {

    }

    @Override
    public void enterDirective(SCPPParser.DirectiveContext ctx) {

    }

    @Override
    public void exitDirective(SCPPParser.DirectiveContext ctx) {

    }

    @Override
    public void enterDefineDirective(SCPPParser.DefineDirectiveContext ctx) {

    }

    @Override
    public void exitDefineDirective(SCPPParser.DefineDirectiveContext ctx) {

    }

    @Override
    public void enterIncludeDirective(SCPPParser.IncludeDirectiveContext ctx) {

    }

    @Override
    public void exitIncludeDirective(SCPPParser.IncludeDirectiveContext ctx) {

    }

    @Override
    public void enterCodeBlock(SCPPParser.CodeBlockContext ctx) {

    }

    @Override
    public void exitCodeBlock(SCPPParser.CodeBlockContext ctx) {

    }

    @Override
    public void enterArgumentArray(SCPPParser.ArgumentArrayContext ctx) {

    }

    @Override
    public void exitArgumentArray(SCPPParser.ArgumentArrayContext ctx) {

    }

    @Override
    public void enterFunctionArgumentArray(SCPPParser.FunctionArgumentArrayContext ctx) {

    }

    @Override
    public void exitFunctionArgumentArray(SCPPParser.FunctionArgumentArrayContext ctx) {

    }

    @Override
    public void enterArrayIndex(SCPPParser.ArrayIndexContext ctx) {

    }

    @Override
    public void exitArrayIndex(SCPPParser.ArrayIndexContext ctx) {

    }

    @Override
    public void enterVariable(SCPPParser.VariableContext ctx) {

    }

    @Override
    public void exitVariable(SCPPParser.VariableContext ctx) {

    }

    @Override
    public void enterExpression(SCPPParser.ExpressionContext ctx) {

    }

    @Override
    public void exitExpression(SCPPParser.ExpressionContext ctx) {

    }

    @Override
    public void enterValue(SCPPParser.ValueContext ctx) {

    }

    @Override
    public void exitValue(SCPPParser.ValueContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {
        row = parserRuleContext.start.getLine();
        col = parserRuleContext.start.getCharPositionInLine();
    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }
}
