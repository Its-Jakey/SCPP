package compiler;

import antlr.SCPPLexer;
import antlr.SCPPListener;
import antlr.SCPPParser;
import compiler.memory.*;
import compiler.optimizer.Assembler;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.*;

import static compiler.Getters.*;
import static compiler.Evaluators.*;

public class Compiler implements SCPPListener {
    private static StringBuilder output;
    public static boolean failed;
    private static int row, col;
    private static List<String> messages;
    private static Stack<Integer> tempStack;
    private static int tempN = 0;
    static final Builtins builtins = new Builtins();
    static LinkedHashMap<String, String> constants;
    public static Program currentProgram;
    static LinkedHashMap<String, Program> compiledLibraries;
    static Stack<Switch> switches;
    public static Path topLevelPath;
    static Stack<List<String>> scopes;

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
        Thread.currentThread().stop();
    }

    static void message(String msg) {
        messages.add("Message: " + msg);
    }

    public static void warn(String msg) {
        messages.add(getPrefixMessage() + " warning: " + msg);
    }

    public static void printMessages() {
        messages.forEach(Console.out::println);
    }

    private Program getLibrary(String lib) {
        if (!compiledLibraries.containsKey(lib)) {
            try {
                compiledLibraries.put(lib, compileProgramFromString(CharStreams.fromReader(new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/" + lib + ".sc"))))), 0, lib + ".sc"));
            } catch (IOException e) {
                errorAndKill(e.toString());
            }
        }
        return compiledLibraries.get(lib);
    }

    private static void runWalker(CharStream stream) {
        SCPPLexer lexer = new SCPPLexer(stream);
        SCPPParser parser = new SCPPParser(new CommonTokenStream(lexer));
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                errorAndKill("line " + line + ":" + charPositionInLine + " " + msg);
            }
        });
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new Compiler(), parser.program());
    }

    public static String compile(Path file) throws IOException {
        long startTime = System.currentTimeMillis();
        topLevelPath = file.getParent();

        init();
        currentProgram = new Program(file.getFileName().toString());
        currentProgram.level = 0;

        runWalker(CharStreams.fromPath(file));
        printMessages();

        end();
        long time = System.currentTimeMillis() - startTime;

        if (failed) {
            printMessages();
            Console.err.println("Build failed in " + time / 1000d + " seconds");
        } else
            Console.out.println("Build succeeded in " + time / 1000d + " seconds");
        return getOutput();
    }

    private static void compileLowerLevel(Path file) {
        Program programBackup = currentProgram.clone();
        currentProgram = compileProgram(file, programBackup.level - 1);

        for (Map.Entry<String, Namespace> newSpace : Objects.requireNonNull(currentProgram).namespaces.entrySet()) {
            if (!programBackup.namespaces.containsKey(newSpace.getKey()) && newSpace.getValue().isPubic)
                programBackup.namespaces.put(newSpace.getKey(), newSpace.getValue());
        }
        currentProgram = programBackup;
    }

    private static Program compileProgram(Path path, int level) {
        if (!path.toFile().exists())
            errorAndKill("File '" + path.getFileName() + "' does not exist");

        try {
            return compileProgramFromString(CharStreams.fromPath(path), level, path.getFileName().toString());
        } catch (IOException e) {
            errorAndKill(e.toString());
        }
        return null;
    }

    private static Program compileProgramFromString(CharStream code, int level, String filename) {
        Program programBackup = currentProgram != null ? currentProgram.clone() : null;

        currentProgram = new Program(filename);
        currentProgram.level = level;

        runWalker(code);
        Program compiledProgram = currentProgram.clone();
        currentProgram = programBackup;
        return compiledProgram;
    }

    public static void compileContext(ParserRuleContext ctx) {
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
        compiledLibraries = new LinkedHashMap<>();
        switches = new Stack<>();
        scopes = new Stack<>();

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

    public static String getOperatorInstruction(String op) {
        if (op.equals(".."))
            return "join";

        return switch (op) {
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
            case "^" -> "bitwiseXor";
            default -> {
                errorAndKill("Unexpected operator: " + op);
                printMessages();
                yield "";
            }
        } + "WithVar";
    }

    private void combineNamespace(Namespace source, Namespace definition) {
        for (Map.Entry<String, Function> function : source.functions.entrySet()) {
            if (function.getValue().isPublic)
                definition.functions.put(function.getKey(), Function.changeVisibility(function.getValue(), false));
        }
        for (Map.Entry<String, Variable> variable : source.variables.entrySet()) {
            if (variable.getValue().isPublic())
                definition.variables.put(variable.getKey(), new Variable(variable.getValue().id(), false));
        }
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

                    String fileNameBackup = currentProgram.fileName;
                    currentProgram.fileName = namespace.fileName;
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
        SCPPParser.IdListContext uses = ctx.idList();

        while (uses != null) {
            Namespace namespace = getNamespace(uses.ID().getText());
            combineNamespace(namespace, currentProgram.currentNamespace);
            uses = uses.idList();
        }
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

    private static final Stack<Integer> ifCounts = new Stack<>();
    private static int ifCount = 0;

    @Override
    public void enterIfStatement(SCPPParser.IfStatementContext ctx) {
        ifCounts.push(ifCount++);
    }

    @Override
    public void exitIfStatement(SCPPParser.IfStatementContext ctx) {
        ifCounts.pop();
    }

    @Override
    public void enterIfPart(SCPPParser.IfPartContext ctx) {
        checkInFunction("if");

        evaluateExpression(ctx.expression());
        appendLine("jf\n%ifExit" + ifCounts.peek() + "%");
    }

    @Override
    public void exitIfPart(SCPPParser.IfPartContext ctx) {
        if (((SCPPParser.IfStatementContext) ctx.getParent()).elsePart() != null)
            appendLine("jmp\n%elseExit" + ifCounts.peek() + "%");
        appendLine(":ifExit" + ifCounts.peek());
    }

    @Override
    public void enterElsePart(SCPPParser.ElsePartContext ctx) {
        appendLine(":elseEnter" + ifCounts.peek());
    }

    @Override
    public void exitElsePart(SCPPParser.ElsePartContext ctx) {
        appendLine(":elseExit" + ifCounts.peek());
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
    public void enterSwitchStatement(SCPPParser.SwitchStatementContext ctx) {
        evaluateExpression(ctx.expression());
        String exit = createTemp();
        endTemp();

        appendLine("storeAtVar\n" + createTemp());
        switches.push(new Switch(endTemp(), exit));
    }

    @Override
    public void exitSwitchStatement(SCPPParser.SwitchStatementContext ctx) {
        appendLine(":" + switches.pop().exitPoint);
    }

    @Override
    public void enterCaseStatement(SCPPParser.CaseStatementContext ctx) {
        appendLine(":" + ctx.hashCode());

        evaluateExpression(ctx.expression());
        appendLine("boolEqualWithVar\n" + switches.peek().switchValue);
        SCPPParser.SwitchStatementContext ssc = ((SCPPParser.SwitchStatementContext) ctx.parent);

        if (ssc.caseStatement().indexOf(ctx) == ssc.caseStatement().size() - 1) { //If this is the last case statement in the chain
            if (ssc.defaultStatement() != null)
                appendLine("jf\n%" + switches.peek().hashCode() + "default%"); //Jump to the default statement
            else
                appendLine("jf\n%" + switches.peek().exitPoint + "%");
        } else {
            //Not a real label for some reason
            appendLine("jf\n%" + ssc.caseStatement().get(ssc.caseStatement().indexOf(ctx) + 1).hashCode() + "%"); //Jump to the next case statement
        }
    }

    @Override
    public void exitCaseStatement(SCPPParser.CaseStatementContext ctx) {
        appendLine("jmp\n%" + switches.peek().exitPoint + "%");
    }

    @Override
    public void enterDefaultStatement(SCPPParser.DefaultStatementContext ctx) {
        appendLine(":" + switches.peek().hashCode() + "default");
    }

    @Override
    public void exitDefaultStatement(SCPPParser.DefaultStatementContext ctx) {

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
        if (currentProgram.currentNamespace == null) {
            error("Cannot define variable outside of namespace bounds");
            return;
        }
        String varName;
        if (currentProgram.currentFunction != null) {
            if (currentProgram.currentFunction.localVariables.containsKey(ctx.ID().getText()) || currentProgram.currentFunction.arguments.containsKey(ctx.ID().getText()))
                error("Duplicate variable '" + ctx.ID().getText() + "'");

            varName = currentProgram.currentNamespace.name + "_" + currentProgram.currentFunction.name + "_" + ctx.ID().getText();
            currentProgram.currentFunction.localVariables.put(ctx.ID().getText(), varName);
            scopes.peek().add(ctx.ID().getText());
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

        if (ctx.VARIABLE_MODIFIER() != null) {
            if (ctx.arrayIndex() != null)
                error("Cannot use array index with +=, -=, *=, and /=");
            else {
                String mod = ctx.VARIABLE_MODIFIER().getText();
                String tmp = mod.substring(0, mod.length() - 1);

                if (tmp.equals("..")) {
                    evaluateExpression(ctx.expression());
                    appendLine("storeAtVar\nconcatTmp");
                    appendLine("loadAtVar\n" + var.id());
                    appendLine(getOperatorInstruction(tmp) + "\nconcatTmp");
                } else {
                    evaluateExpression(ctx.expression());
                    appendLine(getOperatorInstruction(tmp) + "\n" + var.id());
                }
                appendLine("storeAtVar\n" + var.id());
            }
        } else if (ctx.VARIABLE_SINGLE_MODIFIER() != null) {
            if (ctx.VARIABLE_SINGLE_MODIFIER().getText().equals("++"))
                appendLine("inc\n" + var.id());
            else
                appendLine("dec\n" + var.id());
        } else {
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
        //TODO: Make inline functions non-returnable
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
    }

    @Override
    public void enterIncludeDirective(SCPPParser.IncludeDirectiveContext ctx) {

    }

    @Override
    public void exitIncludeDirective(SCPPParser.IncludeDirectiveContext ctx) {
        Program program;

        if (ctx.LIBRARY() != null) {
            String lib = ctx.LIBRARY().getText().substring(1, ctx.LIBRARY().getText().length() - 1);
            program = getLibrary(lib);
            //Console.out.println(program.fileName);
        } else {
            String tmp = ctx.STRING().getText();
            String path = topLevelPath + "/" + tmp.substring(1, tmp.length() - 1);

            if (!compiledLibraries.containsKey(path))
                compiledLibraries.put(path, compileProgram(Path.of(path), currentProgram.level + 1));
            program = compiledLibraries.get(path);

            if (program.namespaces.values().stream().anyMatch(Objects::isNull))
                errorAndKill("Found null namespace while compiling");
        }
        for (Map.Entry<String, Namespace> namespace : program.namespaces.entrySet()) {
            if (!namespace.getValue().isPubic || currentProgram.namespaces.containsKey(namespace.getKey()))
                continue;
            currentProgram.namespaces.put(namespace.getKey(), namespace.getValue());
        }
    }

    @Override
    public void enterCodeBlock(SCPPParser.CodeBlockContext ctx) {
        if (currentProgram.currentFunction != null)
            scopes.push(new ArrayList<>());
    }

    @Override
    public void exitCodeBlock(SCPPParser.CodeBlockContext ctx) {
        if (currentProgram.currentFunction != null) {
            List<String> variablesToRemove = scopes.pop();

            for (String variable : variablesToRemove)
                currentProgram.currentFunction.localVariables.remove(variable);
        }
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
    public void enterConditionalValue(SCPPParser.ConditionalValueContext ctx) {

    }

    @Override
    public void exitConditionalValue(SCPPParser.ConditionalValueContext ctx) {

    }

    @Override
    public void enterIdList(SCPPParser.IdListContext ctx) {

    }

    @Override
    public void exitIdList(SCPPParser.IdListContext ctx) {

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
