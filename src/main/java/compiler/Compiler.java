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
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

import static compiler.Getters.*;
import static compiler.Evaluators.*;

public class Compiler implements SCPPListener {
    private static StringBuilder output;
    private static boolean failed;
    static String fileName = null;
    private static int row, col;
    private static List<String> messages;
    static LinkedHashMap<String, Namespace> namespaces;
    static Namespace currentNamespace;
    static Function currentFunction;
    private static Stack<Integer> tempStack;
    private static int tempN = 0;
    static final Builtins builtins = new Builtins();
    static LinkedHashMap<String, String> constants;
    public static boolean showLogs = false;

    public static String getPrefixMessage() {
        return fileName + " " + row + ":" + col;
    }
    public static void error(String msg) {
        messages.add(getPrefixMessage() + " error: " + msg);
        failed = true;
    }
    public static void errorAndKill(String msg) {
        error(msg);
        printMessages();
        System.exit(1);
    }

    static void log(Object msg) {
        if (showLogs)
            System.out.println("Log:\t" + msg);
    }
    public static void warn(String msg) {
        messages.add(getPrefixMessage() + " warning: " + msg);
    }

    private String getVariableName(SCPPParser.VariableContext ctx) {
        return "";
    }

    public static void printMessages() {
        messages.forEach(System.out::println);
    }

    private static void runWalker(CharStream stream) {
        SCPPLexer lexer = new SCPPLexer(stream);
        SCPPParser parser = new SCPPParser(new CommonTokenStream(lexer));
        ParseTreeWalker walker = new ParseTreeWalker();

        walker.walk(new Compiler(), parser.program());
    }

    public static String compile(Path file) throws IOException {
        long startTime = System.currentTimeMillis();

        init();
        fileName = file.getFileName().toString();

        runWalker(CharStreams.fromPath(file));
        printMessages();

        end();
        long time = System.currentTimeMillis() - startTime;

        if (failed) {
            printMessages();
            System.err.println("Build failed in " + time / 1000d + " seconds");
        }
        else
            System.out.println("Build succeeded in " + time / 1000d + " seconds");
        return getOutput();
    }

    private static void compileLowerLevel(Path file) {
        String filenameBackup = fileName.substring(0);
        Namespace namespaceBackup = currentNamespace;
        Function functionBackup = currentFunction;
        fileName = file.getFileName().toString();

        try {
            runWalker(CharStreams.fromPath(file));
        } catch (Exception e) {
            errorAndKill("Failed to compile file '" + fileName + "', " + e.getMessage());
            failed = true;
        }
        fileName = filenameBackup;
        currentNamespace = namespaceBackup;
        currentFunction = functionBackup;
    }

    private static void compileContext(ParserRuleContext ctx) {
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new Compiler(), ctx);
    }

    private static void init() {
        tempN = 0;
        currentNamespace = null;
        currentFunction = null;
        failed = false;
        output = new StringBuilder();
        messages = new ArrayList<>();
        namespaces = new LinkedHashMap<>();
        constants = new LinkedHashMap<>();
        tempStack = new Stack<>();

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

    private static void checkInFunction(String keyword) {
        if (currentFunction == null)
            errorAndKill("Cannot use '" + keyword + "' keyword outside of function scope");
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
        log("Entered namespace '" + ctx.ID(0).getText() + "'");

        if (currentNamespace != null)
            errorAndKill("Cannot declare namespace from inside of namespace");
        if (namespaces.containsKey(ctx.ID(0).getText()))
            errorAndKill("Duplicate namespace '" + ctx.ID(0).getText() + "'");

        if (ctx.codeBlock() == null) {
            if (!namespaces.containsKey(ctx.ID(1).getText()))
                error("Unknown namespace '" + ctx.ID(1).getText() + "'");
            else {
                Namespace namespace = namespaces.get(ctx.ID(1).getText());

                if (namespace.context.codeBlock() == null)
                    error("Namespaces cannot be cloned from a namespace that is not a source (wut am I trying to say here)");
                else {
                    TerminalNode newId = new TerminalNodeImpl(new CommonToken(SCPPParser.ID, ctx.ID(0).getText()));

                    //namespace.ID().set(0, newId); //Not changing ID
                    namespace.context.children.set(0, newId);

                    String fileNameBackup = fileName.substring(0);
                    fileName = namespace.fileName.substring(0);
                    compileContext(namespace.context);

                    fileName = fileNameBackup;
                }
            }
            return;
        }
        currentNamespace = new Namespace(ctx.ID(0).getText(), ctx.pub != null);
        currentNamespace.context = ctx;
        currentNamespace.fileName = fileName;
        appendLine(":" + currentNamespace.name);
    }

    @Override
    public void exitNamespaceDeclaration(SCPPParser.NamespaceDeclarationContext ctx) {
        if (ctx.codeBlock() != null) {
            namespaces.put(currentNamespace.name, currentNamespace);
            currentNamespace = null;
            appendLine("ret");
        }
        log("Exited namespace '" + ctx.ID(0).getText() + "'");
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
        currentFunction.context = ctx;

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
        checkInFunction("if");

        evaluateExpression(ctx.expression());
        appendLine("jf\n%" + createTemp() + "%");
    }

    @Override
    public void exitIfStatement(SCPPParser.IfStatementContext ctx) {
        appendLine(":" + endTemp());
    }

    @Override
    public void enterWhileLoop(SCPPParser.WhileLoopContext ctx) {
        checkInFunction("while");

        evaluateExpression(ctx.expression());
        appendLine("jf\n%" + createTemp() + "%");
        appendLine(":" + createTemp());
    }

    @Override
    public void exitWhileLoop(SCPPParser.WhileLoopContext ctx) {
        evaluateExpression(ctx.expression());
        appendLine("jt\n%" + endTemp() + "%");
        appendLine(":" + endTemp());
    }

    @Override
    public void enterForLoop(SCPPParser.ForLoopContext ctx) {
        checkInFunction("for");
        String idxName = ctx.ID().getText();

        if (currentFunction.localVariables.containsKey(idxName) || currentFunction.arguments.containsKey(idxName))
            errorAndKill("Duplicate variable '" + idxName + "'");
        String varName = currentNamespace.name + "_" + currentFunction.name + "_" + idxName;

        evaluateExpression(ctx.expression(0));
        appendLine("storeAtVar\n" + varName);

        if (ctx.expression().size() > 2) {
            evaluateExpression(ctx.expression(2));
            appendLine("storeAtVar\n" + createTemp());
        }
        evaluateExpression(ctx.expression(1));
        String highValueName = createTemp();
        endTemp();
        appendLine("storeAtVar\n" + highValueName);
        appendLine(":" + createTemp());

        appendLine("loadAtVar\n" + varName);
        appendLine("largerThanOrEqualWithVar\n" + highValueName);
        appendLine("jt\n%" + createTemp() + "%");

        currentFunction.localVariables.put(ctx.ID().getText(), varName);

        /*
        tempStack
        -----------
        forEnd
        boundsCheck
        increment?
        -----------
         */
    }

    @Override
    public void exitForLoop(SCPPParser.ForLoopContext ctx) {
        String forEnd = endTemp(), boundsCheck = endTemp();
        String varName = currentFunction.localVariables.get(ctx.ID().getText());

        if (ctx.expression().size() > 2) { //TODO: Add "changeVarBy" instruction
            appendLine("loadAtVar\n" + endTemp());
            appendLine("addWithVar\n" + varName);
            appendLine("storeAtVar\n" + varName);
        } else
            appendLine("inc\n" + varName);
        appendLine("jmp\n%" + boundsCheck + "%");
        appendLine(":" + forEnd);

        currentFunction.localVariables.remove(ctx.ID().getText());
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
        if (currentNamespace == null)
            error("Cannot define variable outside of namespace bounds");
        String varName;
        if (currentFunction != null) {
            if (currentFunction.localVariables.containsKey(ctx.ID().getText()) || currentFunction.arguments.containsKey(ctx.ID().getText()))
                error("Duplicate variable '" + ctx.ID().getText() + "'");

            varName = currentNamespace.name + "_" + currentFunction.name + "_" + ctx.ID().getText();
            currentFunction.localVariables.put(ctx.ID().getText(), varName);
        } else {
            if (currentNamespace.variables.containsKey(ctx.ID().getText()))
                error("Duplicate variable '" + ctx.ID().getText() + "'");
            varName = currentNamespace.name + "_" + ctx.ID().getText();
            currentNamespace.variables.put(ctx.ID().getText(), new Variable(varName, ctx.pub != null));
        }
        evaluateExpression(ctx.expression());
        appendLine("storeAtVar\n" + varName);
    }

    @Override
    public void enterVariableValueChange(SCPPParser.VariableValueChangeContext ctx) {

    }

    @Override
    public void exitVariableValueChange(SCPPParser.VariableValueChangeContext ctx) {
        if (currentFunction == null)
            errorAndKill("Cannot change variable value outside of function bounds.");
        Variable var;
        SCPPParser.VariableContext variable = ctx.variable();

        if (ctx.variable().variable() != null)
            var = getVariable(getNamespace(variable.ID().getText()), null, variable.variable().getText());
        else
            var = getVariable(currentNamespace, currentFunction, variable.ID().getText());

        if (ctx.VARIABLE_SINGLE_MODIFIER() != null || ctx.VARIABLE_MODIFIER() != null) //TODO: Implement variable single modifiers
            error("++, --, +=, -=, *=, and /= have not been implemented yet");
        else {
            evaluateExpression(ctx.expression());
            appendLine("storeAtVar\n" + var.id());
        }
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
        checkInFunction("return");
        evaluateExpression(ctx.expression());
        appendLine("storeAtVar\n" + currentFunction.returnVariable);
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
        addConstant(ctx.ID().getText(), ctx.INT().getText());
        log("Constant '" + ctx.ID().getText() + "' defined");
    }

    @Override
    public void enterIncludeDirective(SCPPParser.IncludeDirectiveContext ctx) {

    }

    @Override
    public void exitIncludeDirective(SCPPParser.IncludeDirectiveContext ctx) {
        String path;

        if (ctx.LIBRARY() != null)
            path = "lib/" + ctx.LIBRARY().getText().substring(1, ctx.LIBRARY().getText().length() - 1) + ".sc";
        else
            path = ctx.STRING().getText().substring(1, ctx.STRING().getText().length() - 1);
        compileLowerLevel(Path.of(path));
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
