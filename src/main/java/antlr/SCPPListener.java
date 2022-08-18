package antlr;// Generated from SCPP.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SCPPParser}.
 */
public interface SCPPListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SCPPParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(SCPPParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(SCPPParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(SCPPParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(SCPPParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#bracketStatement}.
	 * @param ctx the parse tree
	 */
	void enterBracketStatement(SCPPParser.BracketStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#bracketStatement}.
	 * @param ctx the parse tree
	 */
	void exitBracketStatement(SCPPParser.BracketStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#namespaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceDeclaration(SCPPParser.NamespaceDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#namespaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceDeclaration(SCPPParser.NamespaceDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(SCPPParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(SCPPParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(SCPPParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(SCPPParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#ifPart}.
	 * @param ctx the parse tree
	 */
	void enterIfPart(SCPPParser.IfPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#ifPart}.
	 * @param ctx the parse tree
	 */
	void exitIfPart(SCPPParser.IfPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#elsePart}.
	 * @param ctx the parse tree
	 */
	void enterElsePart(SCPPParser.ElsePartContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#elsePart}.
	 * @param ctx the parse tree
	 */
	void exitElsePart(SCPPParser.ElsePartContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#whileLoop}.
	 * @param ctx the parse tree
	 */
	void enterWhileLoop(SCPPParser.WhileLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#whileLoop}.
	 * @param ctx the parse tree
	 */
	void exitWhileLoop(SCPPParser.WhileLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void enterForLoop(SCPPParser.ForLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void exitForLoop(SCPPParser.ForLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#switchStatement}.
	 * @param ctx the parse tree
	 */
	void enterSwitchStatement(SCPPParser.SwitchStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#switchStatement}.
	 * @param ctx the parse tree
	 */
	void exitSwitchStatement(SCPPParser.SwitchStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void enterCaseStatement(SCPPParser.CaseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void exitCaseStatement(SCPPParser.CaseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#defaultStatement}.
	 * @param ctx the parse tree
	 */
	void enterDefaultStatement(SCPPParser.DefaultStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#defaultStatement}.
	 * @param ctx the parse tree
	 */
	void exitDefaultStatement(SCPPParser.DefaultStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#nonBracketStatement}.
	 * @param ctx the parse tree
	 */
	void enterNonBracketStatement(SCPPParser.NonBracketStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#nonBracketStatement}.
	 * @param ctx the parse tree
	 */
	void exitNonBracketStatement(SCPPParser.NonBracketStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(SCPPParser.VariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(SCPPParser.VariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#variableValueChange}.
	 * @param ctx the parse tree
	 */
	void enterVariableValueChange(SCPPParser.VariableValueChangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#variableValueChange}.
	 * @param ctx the parse tree
	 */
	void exitVariableValueChange(SCPPParser.VariableValueChangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(SCPPParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(SCPPParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(SCPPParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(SCPPParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#directive}.
	 * @param ctx the parse tree
	 */
	void enterDirective(SCPPParser.DirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#directive}.
	 * @param ctx the parse tree
	 */
	void exitDirective(SCPPParser.DirectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#defineDirective}.
	 * @param ctx the parse tree
	 */
	void enterDefineDirective(SCPPParser.DefineDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#defineDirective}.
	 * @param ctx the parse tree
	 */
	void exitDefineDirective(SCPPParser.DefineDirectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#includeDirective}.
	 * @param ctx the parse tree
	 */
	void enterIncludeDirective(SCPPParser.IncludeDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#includeDirective}.
	 * @param ctx the parse tree
	 */
	void exitIncludeDirective(SCPPParser.IncludeDirectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#codeBlock}.
	 * @param ctx the parse tree
	 */
	void enterCodeBlock(SCPPParser.CodeBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#codeBlock}.
	 * @param ctx the parse tree
	 */
	void exitCodeBlock(SCPPParser.CodeBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#argumentArray}.
	 * @param ctx the parse tree
	 */
	void enterArgumentArray(SCPPParser.ArgumentArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#argumentArray}.
	 * @param ctx the parse tree
	 */
	void exitArgumentArray(SCPPParser.ArgumentArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#functionArgumentArray}.
	 * @param ctx the parse tree
	 */
	void enterFunctionArgumentArray(SCPPParser.FunctionArgumentArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#functionArgumentArray}.
	 * @param ctx the parse tree
	 */
	void exitFunctionArgumentArray(SCPPParser.FunctionArgumentArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#arrayIndex}.
	 * @param ctx the parse tree
	 */
	void enterArrayIndex(SCPPParser.ArrayIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#arrayIndex}.
	 * @param ctx the parse tree
	 */
	void exitArrayIndex(SCPPParser.ArrayIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(SCPPParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(SCPPParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(SCPPParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(SCPPParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(SCPPParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(SCPPParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#conditionalValue}.
	 * @param ctx the parse tree
	 */
	void enterConditionalValue(SCPPParser.ConditionalValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#conditionalValue}.
	 * @param ctx the parse tree
	 */
	void exitConditionalValue(SCPPParser.ConditionalValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SCPPParser#idList}.
	 * @param ctx the parse tree
	 */
	void enterIdList(SCPPParser.IdListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SCPPParser#idList}.
	 * @param ctx the parse tree
	 */
	void exitIdList(SCPPParser.IdListContext ctx);
}