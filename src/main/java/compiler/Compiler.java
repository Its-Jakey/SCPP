package compiler;

import antlr.SCPPLexer;
import antlr.SCPPListener;
import antlr.SCPPParser;
import compiler.optimizer.Assembler;
import compiler.optimizer.Optimizer;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

import static compiler.Getters.*;
import static compiler.Evaluators.*;

public class Compiler implements SCPPListener {
    private static StringBuilder output;
    private static boolean failed;
    private static int row, col;
    private static List<String> messages;
    private static Stack<Integer> tempStack;
    private static int tempN = 0;
    static final Builtins builtins = new Builtins();
    static LinkedHashMap<String, String> constants;
    public static boolean showLogs = false;
    public static boolean optimize = false;
    static Program currentProgram;
    static LinkedHashMap<String, Program> compiledLibraries;

    public static String getPrefixMessage() {
        return currentProgram.fileName + " " + row + ":" + col;
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
            messages.add("Log:\t" + msg);
    }

    static void message(String msg) {
        messages.add("Message: " + msg);
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

    private Program getLibrary(String lib) {
        if (!compiledLibraries.containsKey(lib))
            compiledLibraries.put(lib, compileProgram(Path.of("lib/" + lib + ".sc"), 0));
        return compiledLibraries.get(lib);
    }

    private static void runWalker(CharStream stream) {
        SCPPLexer lexer = new SCPPLexer(stream);
        SCPPParser parser = new SCPPParser(new CommonTokenStream(lexer));
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                error("line " + line + ":" + charPositionInLine + " " + msg);
            }
        });
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new Compiler(), parser.program());
    }

    public static String compile(Path file) throws IOException {
        long startTime = System.currentTimeMillis();

        init();
        currentProgram = new Program(file.getFileName().toString());
        currentProgram.level = 0;

        runWalker(CharStreams.fromPath(file));
        printMessages();

        end();
        long time = System.currentTimeMillis() - startTime;

        if (failed) {
            printMessages();
            System.err.println("Build failed in " + time / 1000d + " seconds");
        } else
            System.out.println("Build succeeded in " + time / 1000d + " seconds");
        if (optimize)
            return Assembler.assemble(Optimizer.optimize(getRawOutput()));
        else
            return getOutput();
    }

    private static void compileLowerLevel(Path file) {
        Program programBackup = currentProgram.clone();
        currentProgram = compileProgram(file, programBackup.level - 1);

        for (Map.Entry<String, Namespace> newSpace : currentProgram.namespaces.entrySet()) {
            if (!programBackup.namespaces.containsKey(newSpace.getKey()) && newSpace.getValue().isPubic)
                programBackup.namespaces.put(newSpace.getKey(), newSpace.getValue());
        }
        currentProgram = programBackup;
    }

    private static Program compileProgram(Path path, int level) {
        Program programBackup = currentProgram != null ? currentProgram.clone() : null;

        currentProgram = new Program(path.getFileName().toString());
        currentProgram.level = level;

        try {
            runWalker(CharStreams.fromPath(path));
        } catch (IOException e) {
            errorAndKill("Failed to compile file '" + currentProgram.fileName + "', " + e.getMessage());
            failed = true;
        }
        Program compiledProgram = currentProgram.clone();
        currentProgram = programBackup;
        return compiledProgram;
    }

    static void compileContext(ParserRuleContext ctx) {
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new Compiler(), ctx);
    }

    private static void init() {
        tempN = 0;
        failed = false;
        output = new StringBuilder();
        messages = new ArrayList<>();
        constants = new LinkedHashMap<>();
        tempStack = new Stack<>();

        appendLine("jmp\n%ENTRY%");
    }

    private static void end() {
        appendLine(":ENTRY");
        Namespace mainNamespace = null;

        for (Namespace namespace : currentProgram.namespaces.values()) {
            appendLine("jts\n%" + namespace.name + "%");

            if (namespace.functions.containsKey(Function.getID("main", 0)) && namespace.functions.get(Function.getID("main", 0)).isPublic)
                mainNamespace = namespace;
        }
        if (mainNamespace == null)
            error("main function not found, try defining one with 'public func main(){}'");
        else
            appendLine("jts\n%" + mainNamespace.functions.get(Function.getID("main", 0)).getLabel() + "%\ndone");
    }

    public static void appendLine(Object line) {
        output.append("\n").append(line);
    }

    public static String getOutput() {
        if (output.length() == 0)
            return "";
        return Assembler.assemble(output.substring(1));
    }

    public static String getRawOutput() {
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
        if (currentProgram.currentFunction == null)
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

        if (currentProgram.currentNamespace != null)
            errorAndKill("Cannot declare namespace from inside of namespace");
        if (currentProgram.namespaces.containsKey(ctx.ID(0).getText()))
            errorAndKill("Duplicate namespace '" + ctx.ID(0).getText() + "'");

        if (ctx.codeBlock() == null) {
            if (!currentProgram.namespaces.containsKey(ctx.ID(1).getText()))
                error("Unknown namespace '" + ctx.ID(1).getText() + "'");
            else {
                Namespace namespace = currentProgram.namespaces.get(ctx.ID(1).getText());

                if (namespace.context.codeBlock() == null)
                    error("Namespaces cannot be cloned from a namespace that is not a source (wut am I trying to say here)");
                else {
                    TerminalNode newId = new TerminalNodeImpl(new CommonToken(SCPPParser.ID, ctx.ID(0).getText()));

                    //namespace.ID().set(0, newId); //Not changing ID
                    namespace.context.children.set(0, newId);

                    String fileNameBackup = currentProgram.fileName.substring(0);
                    currentProgram.fileName = namespace.fileName.substring(0);
                    compileContext(namespace.context);

                    currentProgram.fileName = fileNameBackup;
                }
            }
            return;
        }
        currentProgram.currentNamespace = new Namespace(ctx.ID(0).getText(), ctx.pub != null);
        currentProgram.currentNamespace.context = ctx;
        currentProgram.currentNamespace.fileName = currentProgram.fileName;
        currentProgram.currentNamespace.level = currentProgram.level;
        appendLine(":" + currentProgram.currentNamespace.name);
    }

    @Override
    public void exitNamespaceDeclaration(SCPPParser.NamespaceDeclarationContext ctx) {
        if (ctx.codeBlock() != null) {
            NamespaceBuiltins.addTo(currentProgram.currentNamespace);
            currentProgram.namespaces.put(currentProgram.currentNamespace.name, currentProgram.currentNamespace);
            currentProgram.currentNamespace = null;
            appendLine("ret");
        }
        log("Exited namespace '" + ctx.ID(0).getText() + "'");
    }

    @Override
    public void enterFunctionDeclaration(SCPPParser.FunctionDeclarationContext ctx) {
        if (currentProgram.currentFunction != null)
            errorAndKill("Cannot define function from inside of function");
        if (currentProgram.currentNamespace == null)
            errorAndKill("Cannot define function from outside of namespace");
        List<String> args = evaluateFunctionArgumentArray(ctx.functionArgumentArray());
        String name = ctx.ID().getText();

        if (currentProgram.currentNamespace.functions.containsKey(Function.getID(name, args.size())) || builtins.functions.containsKey(Function.getID(name, args.size())))
            errorAndKill("Duplicate function '" + name);
        currentProgram.currentFunction = new Function(name, args, ctx.pub != null, currentProgram.currentNamespace.name + "_");
        currentProgram.currentFunction.level = currentProgram.currentNamespace.level;
        currentProgram.currentFunction.inline = ctx.inline != null;
        currentProgram.currentFunction.context = ctx;
        currentProgram.currentFunction.program = currentProgram;

        appendLine("jmp\n%" + currentProgram.currentFunction.getLabel() + "_end%");
        if (ctx.inline == null)
            appendLine(":" + currentProgram.currentFunction.getLabel());
    }

    @Override
    public void exitFunctionDeclaration(SCPPParser.FunctionDeclarationContext ctx) {
        currentProgram.currentNamespace.functions.put(currentProgram.currentFunction.getID(), currentProgram.currentFunction);
        if (ctx.inline != null)
            appendLine("ret");
        appendLine(":" + currentProgram.currentFunction.getLabel() + "_end");
        currentProgram.currentFunction = null;
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

        if (currentProgram.currentFunction.localVariables.containsKey(idxName) || currentProgram.currentFunction.arguments.containsKey(idxName))
            errorAndKill("Duplicate variable '" + idxName + "'");
        String varName = currentProgram.currentNamespace.name + "_" + currentProgram.currentFunction.name + "_" + idxName;

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

        currentProgram.currentFunction.localVariables.put(ctx.ID().getText(), varName);

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
        String varName = currentProgram.currentFunction.localVariables.get(ctx.ID().getText());

        if (ctx.expression().size() > 2) { //TODO: Add "changeVarBy" instruction
            appendLine("loadAtVar\n" + endTemp());
            appendLine("addWithVar\n" + varName);
            appendLine("storeAtVar\n" + varName);
        } else
            appendLine("inc\n" + varName);
        appendLine("jmp\n%" + boundsCheck + "%");
        appendLine(":" + forEnd);

        currentProgram.currentFunction.localVariables.remove(ctx.ID().getText());
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
        if (currentProgram.currentNamespace == null)
            error("Cannot define variable outside of namespace bounds");
        String varName;
        if (currentProgram.currentFunction != null) {
            if (currentProgram.currentFunction.localVariables.containsKey(ctx.ID().getText()) || currentProgram.currentFunction.arguments.containsKey(ctx.ID().getText()))
                error("Duplicate variable '" + ctx.ID().getText() + "'");

            varName = currentProgram.currentNamespace.name + "_" + currentProgram.currentFunction.name + "_" + ctx.ID().getText();
            currentProgram.currentFunction.localVariables.put(ctx.ID().getText(), varName);
        } else {
            if (currentProgram.currentNamespace.variables.containsKey(ctx.ID().getText()))
                error("Duplicate variable '" + ctx.ID().getText() + "'");
            varName = currentProgram.currentNamespace.name + "_" + ctx.ID().getText();
            currentProgram.currentNamespace.variables.put(ctx.ID().getText(), new Variable(varName, ctx.pub != null));
        }
        if (ctx.expression() != null) {
            evaluateExpression(ctx.expression());
            appendLine("storeAtVar\n" + varName);
        }
    }

    @Override
    public void enterVariableValueChange(SCPPParser.VariableValueChangeContext ctx) {

    }

    @Override
    public void exitVariableValueChange(SCPPParser.VariableValueChangeContext ctx) {
        if (currentProgram.currentFunction == null)
            errorAndKill("Cannot change variable value outside of function bounds.");
        Variable var;
        SCPPParser.VariableContext variable = ctx.variable();

        if (ctx.variable().variable() != null)
            var = getVariable(getNamespace(variable.ID().getText()), null, variable.variable().getText());
        else
            var = getVariable(currentProgram.currentNamespace, currentProgram.currentFunction, variable.ID().getText());

        if (ctx.VARIABLE_SINGLE_MODIFIER() != null || ctx.VARIABLE_MODIFIER() != null) //TODO: Implement variable single modifiers
            error("++, --, +=, -=, *=, and /= have not been implemented yet");
        else {
            if (ctx.arrayIndex() != null) {
                setValueAtArrayIndex(var.id(), ctx.arrayIndex(), ctx.expression());
                return;
            }
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
        if (ctx.expression() != null) {
            evaluateExpression(ctx.expression());
            appendLine("storeAtVar\n" + currentProgram.currentFunction.returnVariable);
        }
        appendLine("ret");
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
        addConstant(ctx.ID().getText(), ctx.INT() != null ? ctx.INT().getText() : (ctx.HEX() != null ? String.valueOf(Integer.parseInt(ctx.HEX().getText().substring(2), 16)) : String.valueOf(Integer.parseInt(ctx.BIN().getText().substring(2), 2))));
        //addConstant(ctx.ID().getText(), ctx.INT().getText());
        log("Constant '" + ctx.ID().getText() + "' defined");
    }

    @Override
    public void enterIncludeDirective(SCPPParser.IncludeDirectiveContext ctx) {

    }

    @Override
    public void exitIncludeDirective(SCPPParser.IncludeDirectiveContext ctx) {

        if (ctx.LIBRARY() != null) {
            String lib = ctx.LIBRARY().getText().substring(1, ctx.LIBRARY().getText().length() - 1);
            Program program = getLibrary(lib);
            //System.out.println(program.fileName);

            for (Map.Entry<String, Namespace> namespace : program.namespaces.entrySet()) {
                if (!namespace.getValue().isPubic || currentProgram.namespaces.containsKey(namespace.getKey()))
                    continue;
                currentProgram.namespaces.put(namespace.getKey(), namespace.getValue());
            }
            log("Included library <" + lib + ">");
        } else {
            compileLowerLevel(Path.of(ctx.STRING().getText().substring(1, ctx.STRING().getText().length() - 1)));
        }
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
