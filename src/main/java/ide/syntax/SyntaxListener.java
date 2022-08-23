package ide.syntax;

import antlr.SCPPLexer;
import antlr.SCPPListener;
import antlr.SCPPParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

import static antlr.SCPPParser.*;

public class SyntaxListener implements SCPPListener {
    private final StyledDocument doc;
    private final SimpleAttributeSet sas;
    private static final java.util.List<String> keywords = java.util.List.of("public", "namespace", "inline", "func", "var", "from", "to", "by", "uses", "is");
    private static final Color keyword = new Color(0xCC7832);
    private static final Color id = Color.BLACK;
    private static final Color string = new Color(0x6A8759);
    private static final Color comment = new Color(0x629755);
    private static final Color num = new Color(0x6897BB);
    private static final Color directive = new Color(0x12A4EC);

    public SyntaxListener(JTextPane jtp) {
        this.doc = jtp.getStyledDocument();
        this.sas = new SimpleAttributeSet();

        StyleConstants.setForeground(sas, Color.BLACK);
        doc.setCharacterAttributes(0, jtp.getText().length(), sas, false);
    }

    public static void applySyntax(JTextPane jtp) {
        SCPPParser parser = new SCPPParser(new CommonTokenStream(new SCPPLexer(CharStreams.fromString(jtp.getText()))));
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {

            }
        });
        ParseTreeWalker walker = new ParseTreeWalker();
        SyntaxListener listener = new SyntaxListener(jtp);
        walker.walk(listener, parser.program());
    }

    private void light(Token token, Color color) {
        StyleConstants.setForeground(sas, color);
        doc.setCharacterAttributes(token.getStartIndex(), token.getStopIndex() - token.getStartIndex(), sas, false);
    }

    private void light(Token start, Token end, Color color) {
        StyleConstants.setForeground(sas, color);
        doc.setCharacterAttributes(start.getStartIndex(), end.getStopIndex() - start.getStartIndex(), sas, false);
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

    }

    @Override
    public void exitNamespaceDeclaration(SCPPParser.NamespaceDeclarationContext ctx) {

    }

    @Override
    public void enterFunctionDeclaration(SCPPParser.FunctionDeclarationContext ctx) {

    }

    @Override
    public void exitFunctionDeclaration(SCPPParser.FunctionDeclarationContext ctx) {

    }

    @Override
    public void enterIfStatement(SCPPParser.IfStatementContext ctx) {

    }

    @Override
    public void exitIfStatement(SCPPParser.IfStatementContext ctx) {

    }

    @Override
    public void enterIfPart(SCPPParser.IfPartContext ctx) {

    }

    @Override
    public void exitIfPart(SCPPParser.IfPartContext ctx) {

    }

    @Override
    public void enterElsePart(SCPPParser.ElsePartContext ctx) {

    }

    @Override
    public void exitElsePart(SCPPParser.ElsePartContext ctx) {

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
    public void enterSwitchStatement(SCPPParser.SwitchStatementContext ctx) {

    }

    @Override
    public void exitSwitchStatement(SCPPParser.SwitchStatementContext ctx) {

    }

    @Override
    public void enterCaseStatement(SCPPParser.CaseStatementContext ctx) {

    }

    @Override
    public void exitCaseStatement(SCPPParser.CaseStatementContext ctx) {

    }

    @Override
    public void enterDefaultStatement(SCPPParser.DefaultStatementContext ctx) {

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

    }

    @Override
    public void enterReturnStatement(SCPPParser.ReturnStatementContext ctx) {

    }

    @Override
    public void exitReturnStatement(SCPPParser.ReturnStatementContext ctx) {

    }

    @Override
    public void enterDirective(SCPPParser.DirectiveContext ctx) {
        light(ctx.start, ctx.stop, directive);
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
        Token token = terminalNode.getSymbol();

        light(token, switch (token.getType()) {
            case ID -> id;
            case INT, BIN, HEX -> num;
            case LIBRARY, STRING -> string;
            case COMMENT -> comment;
            default -> Color.BLACK;
        });
    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }
}
