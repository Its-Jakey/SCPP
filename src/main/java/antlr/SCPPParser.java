package antlr;// Generated from SCPP.g4 by ANTLR 4.7.1

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SCPPParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, STRING=26, LIBRARY=27, ID=28, INT=29, HEX=30, BIN=31, VARIABLE_MODIFIER=32, 
		VARIABLE_SINGLE_MODIFIER=33, OPERATOR=34, WS=35, SINGLE_COMMENT=36, BLOCK_COMMENT=37;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_bracketStatement = 2, RULE_namespaceDeclaration = 3, 
		RULE_functionDeclaration = 4, RULE_ifStatement = 5, RULE_whileLoop = 6, 
		RULE_forLoop = 7, RULE_nonBracketStatement = 8, RULE_variableDeclaration = 9, 
		RULE_variableValueChange = 10, RULE_functionCall = 11, RULE_returnStatement = 12, 
		RULE_directive = 13, RULE_defineDirective = 14, RULE_includeDirective = 15, 
		RULE_codeBlock = 16, RULE_argumentArray = 17, RULE_functionArgumentArray = 18, 
		RULE_arrayIndex = 19, RULE_variable = 20, RULE_expression = 21, RULE_value = 22;
	public static final String[] ruleNames = {
		"program", "statement", "bracketStatement", "namespaceDeclaration", "functionDeclaration", 
		"ifStatement", "whileLoop", "forLoop", "nonBracketStatement", "variableDeclaration", 
		"variableValueChange", "functionCall", "returnStatement", "directive", 
		"defineDirective", "includeDirective", "codeBlock", "argumentArray", "functionArgumentArray", 
		"arrayIndex", "variable", "expression", "value"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "'public'", "'namespace'", "'is'", "'func'", "'('", "')'", 
		"'if'", "'while'", "'for'", "'from'", "'to'", "'by'", "'var'", "'='", 
		"'return'", "'#'", "'define'", "'include'", "'{'", "'}'", "','", "'['", 
		"']'", "'::'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "STRING", "LIBRARY", "ID", "INT", "HEX", "BIN", "VARIABLE_MODIFIER", 
		"VARIABLE_SINGLE_MODIFIER", "OPERATOR", "WS", "SINGLE_COMMENT", "BLOCK_COMMENT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "SCPP.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SCPPParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(46);
				statement();
				}
				}
				setState(49); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__4) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__13) | (1L << T__15) | (1L << T__16) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public BracketStatementContext bracketStatement() {
			return getRuleContext(BracketStatementContext.class,0);
		}
		public NonBracketStatementContext nonBracketStatement() {
			return getRuleContext(NonBracketStatementContext.class,0);
		}
		public DirectiveContext directive() {
			return getRuleContext(DirectiveContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(56);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(51);
				bracketStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				nonBracketStatement();
				setState(53);
				match(T__0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(55);
				directive();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BracketStatementContext extends ParserRuleContext {
		public NamespaceDeclarationContext namespaceDeclaration() {
			return getRuleContext(NamespaceDeclarationContext.class,0);
		}
		public FunctionDeclarationContext functionDeclaration() {
			return getRuleContext(FunctionDeclarationContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public WhileLoopContext whileLoop() {
			return getRuleContext(WhileLoopContext.class,0);
		}
		public ForLoopContext forLoop() {
			return getRuleContext(ForLoopContext.class,0);
		}
		public BracketStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bracketStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterBracketStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitBracketStatement(this);
		}
	}

	public final BracketStatementContext bracketStatement() throws RecognitionException {
		BracketStatementContext _localctx = new BracketStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_bracketStatement);
		try {
			setState(63);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				namespaceDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				functionDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(60);
				ifStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(61);
				whileLoop();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(62);
				forLoop();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NamespaceDeclarationContext extends ParserRuleContext {
		public Token pub;
		public List<TerminalNode> ID() { return getTokens(SCPPParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SCPPParser.ID, i);
		}
		public CodeBlockContext codeBlock() {
			return getRuleContext(CodeBlockContext.class,0);
		}
		public NamespaceDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespaceDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterNamespaceDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitNamespaceDeclaration(this);
		}
	}

	public final NamespaceDeclarationContext namespaceDeclaration() throws RecognitionException {
		NamespaceDeclarationContext _localctx = new NamespaceDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_namespaceDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(65);
				((NamespaceDeclarationContext)_localctx).pub = match(T__1);
				}
			}

			setState(68);
			match(T__2);
			setState(69);
			match(ID);
			setState(74);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__2:
			case T__4:
			case T__7:
			case T__8:
			case T__9:
			case T__13:
			case T__15:
			case T__16:
			case T__19:
			case ID:
				{
				setState(70);
				codeBlock();
				}
				break;
			case T__3:
				{
				setState(71);
				match(T__3);
				setState(72);
				match(ID);
				setState(73);
				match(T__0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDeclarationContext extends ParserRuleContext {
		public Token pub;
		public TerminalNode ID() { return getToken(SCPPParser.ID, 0); }
		public CodeBlockContext codeBlock() {
			return getRuleContext(CodeBlockContext.class,0);
		}
		public FunctionArgumentArrayContext functionArgumentArray() {
			return getRuleContext(FunctionArgumentArrayContext.class,0);
		}
		public FunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterFunctionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitFunctionDeclaration(this);
		}
	}

	public final FunctionDeclarationContext functionDeclaration() throws RecognitionException {
		FunctionDeclarationContext _localctx = new FunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_functionDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(76);
				((FunctionDeclarationContext)_localctx).pub = match(T__1);
				}
			}

			setState(79);
			match(T__4);
			setState(80);
			match(ID);
			setState(81);
			match(T__5);
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(82);
				functionArgumentArray();
				}
			}

			setState(85);
			match(T__6);
			setState(86);
			codeBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CodeBlockContext codeBlock() {
			return getRuleContext(CodeBlockContext.class,0);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitIfStatement(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(T__7);
			setState(89);
			match(T__5);
			setState(90);
			expression(0);
			setState(91);
			match(T__6);
			setState(92);
			codeBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileLoopContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CodeBlockContext codeBlock() {
			return getRuleContext(CodeBlockContext.class,0);
		}
		public WhileLoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileLoop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterWhileLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitWhileLoop(this);
		}
	}

	public final WhileLoopContext whileLoop() throws RecognitionException {
		WhileLoopContext _localctx = new WhileLoopContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_whileLoop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(T__8);
			setState(95);
			match(T__5);
			setState(96);
			expression(0);
			setState(97);
			match(T__6);
			setState(98);
			codeBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForLoopContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SCPPParser.ID, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public CodeBlockContext codeBlock() {
			return getRuleContext(CodeBlockContext.class,0);
		}
		public ForLoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forLoop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterForLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitForLoop(this);
		}
	}

	public final ForLoopContext forLoop() throws RecognitionException {
		ForLoopContext _localctx = new ForLoopContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_forLoop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(T__9);
			setState(101);
			match(T__5);
			setState(102);
			match(ID);
			setState(103);
			match(T__10);
			setState(104);
			expression(0);
			setState(105);
			match(T__11);
			setState(106);
			expression(0);
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(107);
				match(T__12);
				setState(108);
				expression(0);
				}
			}

			setState(111);
			match(T__6);
			setState(112);
			codeBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NonBracketStatementContext extends ParserRuleContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public VariableValueChangeContext variableValueChange() {
			return getRuleContext(VariableValueChangeContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public NonBracketStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonBracketStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterNonBracketStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitNonBracketStatement(this);
		}
	}

	public final NonBracketStatementContext nonBracketStatement() throws RecognitionException {
		NonBracketStatementContext _localctx = new NonBracketStatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_nonBracketStatement);
		try {
			setState(118);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				variableDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
				variableValueChange();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(116);
				functionCall();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(117);
				returnStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationContext extends ParserRuleContext {
		public Token pub;
		public TerminalNode ID() { return getToken(SCPPParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitVariableDeclaration(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_variableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(120);
				((VariableDeclarationContext)_localctx).pub = match(T__1);
				}
			}

			setState(123);
			match(T__13);
			setState(124);
			match(ID);
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__14) {
				{
				setState(125);
				match(T__14);
				setState(126);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableValueChangeContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode VARIABLE_SINGLE_MODIFIER() { return getToken(SCPPParser.VARIABLE_SINGLE_MODIFIER, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArrayIndexContext arrayIndex() {
			return getRuleContext(ArrayIndexContext.class,0);
		}
		public TerminalNode VARIABLE_MODIFIER() { return getToken(SCPPParser.VARIABLE_MODIFIER, 0); }
		public VariableValueChangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableValueChange; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterVariableValueChange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitVariableValueChange(this);
		}
	}

	public final VariableValueChangeContext variableValueChange() throws RecognitionException {
		VariableValueChangeContext _localctx = new VariableValueChangeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_variableValueChange);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			variable();
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__22) {
				{
				setState(130);
				arrayIndex();
				}
			}

			setState(136);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VARIABLE_SINGLE_MODIFIER:
				{
				setState(133);
				match(VARIABLE_SINGLE_MODIFIER);
				}
				break;
			case T__14:
			case VARIABLE_MODIFIER:
				{
				setState(134);
				_la = _input.LA(1);
				if ( !(_la==T__14 || _la==VARIABLE_MODIFIER) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(135);
				expression(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionCallContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ArgumentArrayContext argumentArray() {
			return getRuleContext(ArgumentArrayContext.class,0);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitFunctionCall(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			variable();
			setState(139);
			match(T__5);
			setState(141);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__19) | (1L << STRING) | (1L << ID) | (1L << INT) | (1L << HEX) | (1L << BIN))) != 0)) {
				{
				setState(140);
				argumentArray();
				}
			}

			setState(143);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitReturnStatement(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(T__15);
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__19) | (1L << STRING) | (1L << ID) | (1L << INT) | (1L << HEX) | (1L << BIN))) != 0)) {
				{
				setState(146);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DirectiveContext extends ParserRuleContext {
		public DefineDirectiveContext defineDirective() {
			return getRuleContext(DefineDirectiveContext.class,0);
		}
		public IncludeDirectiveContext includeDirective() {
			return getRuleContext(IncludeDirectiveContext.class,0);
		}
		public DirectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directive; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitDirective(this);
		}
	}

	public final DirectiveContext directive() throws RecognitionException {
		DirectiveContext _localctx = new DirectiveContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_directive);
		try {
			setState(151);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(149);
				defineDirective();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(150);
				includeDirective();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefineDirectiveContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SCPPParser.ID, 0); }
		public TerminalNode INT() { return getToken(SCPPParser.INT, 0); }
		public DefineDirectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defineDirective; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterDefineDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitDefineDirective(this);
		}
	}

	public final DefineDirectiveContext defineDirective() throws RecognitionException {
		DefineDirectiveContext _localctx = new DefineDirectiveContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_defineDirective);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(T__16);
			setState(154);
			match(T__17);
			setState(155);
			match(ID);
			setState(156);
			match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IncludeDirectiveContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(SCPPParser.STRING, 0); }
		public TerminalNode LIBRARY() { return getToken(SCPPParser.LIBRARY, 0); }
		public IncludeDirectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_includeDirective; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterIncludeDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitIncludeDirective(this);
		}
	}

	public final IncludeDirectiveContext includeDirective() throws RecognitionException {
		IncludeDirectiveContext _localctx = new IncludeDirectiveContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_includeDirective);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(T__16);
			setState(159);
			match(T__18);
			setState(160);
			_la = _input.LA(1);
			if ( !(_la==STRING || _la==LIBRARY) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CodeBlockContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public CodeBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_codeBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterCodeBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitCodeBlock(this);
		}
	}

	public final CodeBlockContext codeBlock() throws RecognitionException {
		CodeBlockContext _localctx = new CodeBlockContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_codeBlock);
		try {
			int _alt;
			setState(171);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__19:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				match(T__19);
				setState(166);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1+1 ) {
						{
						{
						setState(163);
						statement();
						}
						} 
					}
					setState(168);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				}
				setState(169);
				match(T__20);
				}
				break;
			case T__1:
			case T__2:
			case T__4:
			case T__7:
			case T__8:
			case T__9:
			case T__13:
			case T__15:
			case T__16:
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(170);
				statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentArrayContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArgumentArrayContext argumentArray() {
			return getRuleContext(ArgumentArrayContext.class,0);
		}
		public ArgumentArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentArray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterArgumentArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitArgumentArray(this);
		}
	}

	public final ArgumentArrayContext argumentArray() throws RecognitionException {
		ArgumentArrayContext _localctx = new ArgumentArrayContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_argumentArray);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			expression(0);
			setState(176);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(174);
				match(T__21);
				setState(175);
				argumentArray();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionArgumentArrayContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SCPPParser.ID, 0); }
		public FunctionArgumentArrayContext functionArgumentArray() {
			return getRuleContext(FunctionArgumentArrayContext.class,0);
		}
		public FunctionArgumentArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionArgumentArray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterFunctionArgumentArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitFunctionArgumentArray(this);
		}
	}

	public final FunctionArgumentArrayContext functionArgumentArray() throws RecognitionException {
		FunctionArgumentArrayContext _localctx = new FunctionArgumentArrayContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_functionArgumentArray);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(ID);
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(179);
				match(T__21);
				setState(180);
				functionArgumentArray();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayIndexContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArrayIndexContext arrayIndex() {
			return getRuleContext(ArrayIndexContext.class,0);
		}
		public ArrayIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayIndex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterArrayIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitArrayIndex(this);
		}
	}

	public final ArrayIndexContext arrayIndex() throws RecognitionException {
		ArrayIndexContext _localctx = new ArrayIndexContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_arrayIndex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(T__22);
			setState(184);
			expression(0);
			setState(185);
			match(T__23);
			setState(187);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(186);
				arrayIndex();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SCPPParser.ID, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(ID);
			setState(192);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(190);
				match(T__24);
				setState(191);
				variable();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode OPERATOR() { return getToken(SCPPParser.OPERATOR, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 42;
		enterRecursionRule(_localctx, 42, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				{
				setState(195);
				match(T__5);
				setState(196);
				expression(0);
				setState(197);
				match(T__6);
				}
				break;
			case T__19:
			case STRING:
			case ID:
			case INT:
			case HEX:
			case BIN:
				{
				setState(199);
				value();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(207);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expression);
					setState(202);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(203);
					match(OPERATOR);
					setState(204);
					expression(4);
					}
					} 
				}
				setState(209);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ArrayIndexContext arrayIndex() {
			return getRuleContext(ArrayIndexContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public TerminalNode STRING() { return getToken(SCPPParser.STRING, 0); }
		public TerminalNode INT() { return getToken(SCPPParser.INT, 0); }
		public TerminalNode HEX() { return getToken(SCPPParser.HEX, 0); }
		public TerminalNode BIN() { return getToken(SCPPParser.BIN, 0); }
		public ArgumentArrayContext argumentArray() {
			return getRuleContext(ArgumentArrayContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_value);
		try {
			setState(226);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(210);
				variable();
				setState(212);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					{
					setState(211);
					arrayIndex();
					}
					break;
				}
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(214);
				functionCall();
				setState(216);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
				case 1:
					{
					setState(215);
					arrayIndex();
					}
					break;
				}
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(218);
				match(STRING);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(219);
				match(INT);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(220);
				match(HEX);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(221);
				match(BIN);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(222);
				match(T__19);
				setState(223);
				argumentArray();
				setState(224);
				match(T__20);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 21:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\'\u00e7\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\6\2\62"+
		"\n\2\r\2\16\2\63\3\3\3\3\3\3\3\3\3\3\5\3;\n\3\3\4\3\4\3\4\3\4\3\4\5\4"+
		"B\n\4\3\5\5\5E\n\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5M\n\5\3\6\5\6P\n\6\3\6\3"+
		"\6\3\6\3\6\5\6V\n\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tp\n\t\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\5\ny\n\n\3\13\5\13|\n\13\3\13\3\13\3\13\3\13\5\13\u0082"+
		"\n\13\3\f\3\f\5\f\u0086\n\f\3\f\3\f\3\f\5\f\u008b\n\f\3\r\3\r\3\r\5\r"+
		"\u0090\n\r\3\r\3\r\3\16\3\16\5\16\u0096\n\16\3\17\3\17\5\17\u009a\n\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\7\22\u00a7\n\22"+
		"\f\22\16\22\u00aa\13\22\3\22\3\22\5\22\u00ae\n\22\3\23\3\23\3\23\5\23"+
		"\u00b3\n\23\3\24\3\24\3\24\5\24\u00b8\n\24\3\25\3\25\3\25\3\25\5\25\u00be"+
		"\n\25\3\26\3\26\3\26\5\26\u00c3\n\26\3\27\3\27\3\27\3\27\3\27\3\27\5\27"+
		"\u00cb\n\27\3\27\3\27\3\27\7\27\u00d0\n\27\f\27\16\27\u00d3\13\27\3\30"+
		"\3\30\5\30\u00d7\n\30\3\30\3\30\5\30\u00db\n\30\3\30\3\30\3\30\3\30\3"+
		"\30\3\30\3\30\3\30\5\30\u00e5\n\30\3\30\3\u00a8\3,\31\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \"$&(*,.\2\4\4\2\21\21\"\"\3\2\34\35\2\u00f5\2\61"+
		"\3\2\2\2\4:\3\2\2\2\6A\3\2\2\2\bD\3\2\2\2\nO\3\2\2\2\fZ\3\2\2\2\16`\3"+
		"\2\2\2\20f\3\2\2\2\22x\3\2\2\2\24{\3\2\2\2\26\u0083\3\2\2\2\30\u008c\3"+
		"\2\2\2\32\u0093\3\2\2\2\34\u0099\3\2\2\2\36\u009b\3\2\2\2 \u00a0\3\2\2"+
		"\2\"\u00ad\3\2\2\2$\u00af\3\2\2\2&\u00b4\3\2\2\2(\u00b9\3\2\2\2*\u00bf"+
		"\3\2\2\2,\u00ca\3\2\2\2.\u00e4\3\2\2\2\60\62\5\4\3\2\61\60\3\2\2\2\62"+
		"\63\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\3\3\2\2\2\65;\5\6\4\2\66\67"+
		"\5\22\n\2\678\7\3\2\28;\3\2\2\29;\5\34\17\2:\65\3\2\2\2:\66\3\2\2\2:9"+
		"\3\2\2\2;\5\3\2\2\2<B\5\b\5\2=B\5\n\6\2>B\5\f\7\2?B\5\16\b\2@B\5\20\t"+
		"\2A<\3\2\2\2A=\3\2\2\2A>\3\2\2\2A?\3\2\2\2A@\3\2\2\2B\7\3\2\2\2CE\7\4"+
		"\2\2DC\3\2\2\2DE\3\2\2\2EF\3\2\2\2FG\7\5\2\2GL\7\36\2\2HM\5\"\22\2IJ\7"+
		"\6\2\2JK\7\36\2\2KM\7\3\2\2LH\3\2\2\2LI\3\2\2\2M\t\3\2\2\2NP\7\4\2\2O"+
		"N\3\2\2\2OP\3\2\2\2PQ\3\2\2\2QR\7\7\2\2RS\7\36\2\2SU\7\b\2\2TV\5&\24\2"+
		"UT\3\2\2\2UV\3\2\2\2VW\3\2\2\2WX\7\t\2\2XY\5\"\22\2Y\13\3\2\2\2Z[\7\n"+
		"\2\2[\\\7\b\2\2\\]\5,\27\2]^\7\t\2\2^_\5\"\22\2_\r\3\2\2\2`a\7\13\2\2"+
		"ab\7\b\2\2bc\5,\27\2cd\7\t\2\2de\5\"\22\2e\17\3\2\2\2fg\7\f\2\2gh\7\b"+
		"\2\2hi\7\36\2\2ij\7\r\2\2jk\5,\27\2kl\7\16\2\2lo\5,\27\2mn\7\17\2\2np"+
		"\5,\27\2om\3\2\2\2op\3\2\2\2pq\3\2\2\2qr\7\t\2\2rs\5\"\22\2s\21\3\2\2"+
		"\2ty\5\24\13\2uy\5\26\f\2vy\5\30\r\2wy\5\32\16\2xt\3\2\2\2xu\3\2\2\2x"+
		"v\3\2\2\2xw\3\2\2\2y\23\3\2\2\2z|\7\4\2\2{z\3\2\2\2{|\3\2\2\2|}\3\2\2"+
		"\2}~\7\20\2\2~\u0081\7\36\2\2\177\u0080\7\21\2\2\u0080\u0082\5,\27\2\u0081"+
		"\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\25\3\2\2\2\u0083\u0085\5*\26\2"+
		"\u0084\u0086\5(\25\2\u0085\u0084\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u008a"+
		"\3\2\2\2\u0087\u008b\7#\2\2\u0088\u0089\t\2\2\2\u0089\u008b\5,\27\2\u008a"+
		"\u0087\3\2\2\2\u008a\u0088\3\2\2\2\u008b\27\3\2\2\2\u008c\u008d\5*\26"+
		"\2\u008d\u008f\7\b\2\2\u008e\u0090\5$\23\2\u008f\u008e\3\2\2\2\u008f\u0090"+
		"\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0092\7\t\2\2\u0092\31\3\2\2\2\u0093"+
		"\u0095\7\22\2\2\u0094\u0096\5,\27\2\u0095\u0094\3\2\2\2\u0095\u0096\3"+
		"\2\2\2\u0096\33\3\2\2\2\u0097\u009a\5\36\20\2\u0098\u009a\5 \21\2\u0099"+
		"\u0097\3\2\2\2\u0099\u0098\3\2\2\2\u009a\35\3\2\2\2\u009b\u009c\7\23\2"+
		"\2\u009c\u009d\7\24\2\2\u009d\u009e\7\36\2\2\u009e\u009f\7\37\2\2\u009f"+
		"\37\3\2\2\2\u00a0\u00a1\7\23\2\2\u00a1\u00a2\7\25\2\2\u00a2\u00a3\t\3"+
		"\2\2\u00a3!\3\2\2\2\u00a4\u00a8\7\26\2\2\u00a5\u00a7\5\4\3\2\u00a6\u00a5"+
		"\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9"+
		"\u00ab\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab\u00ae\7\27\2\2\u00ac\u00ae\5"+
		"\4\3\2\u00ad\u00a4\3\2\2\2\u00ad\u00ac\3\2\2\2\u00ae#\3\2\2\2\u00af\u00b2"+
		"\5,\27\2\u00b0\u00b1\7\30\2\2\u00b1\u00b3\5$\23\2\u00b2\u00b0\3\2\2\2"+
		"\u00b2\u00b3\3\2\2\2\u00b3%\3\2\2\2\u00b4\u00b7\7\36\2\2\u00b5\u00b6\7"+
		"\30\2\2\u00b6\u00b8\5&\24\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8"+
		"\'\3\2\2\2\u00b9\u00ba\7\31\2\2\u00ba\u00bb\5,\27\2\u00bb\u00bd\7\32\2"+
		"\2\u00bc\u00be\5(\25\2\u00bd\u00bc\3\2\2\2\u00bd\u00be\3\2\2\2\u00be)"+
		"\3\2\2\2\u00bf\u00c2\7\36\2\2\u00c0\u00c1\7\33\2\2\u00c1\u00c3\5*\26\2"+
		"\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3+\3\2\2\2\u00c4\u00c5\b"+
		"\27\1\2\u00c5\u00c6\7\b\2\2\u00c6\u00c7\5,\27\2\u00c7\u00c8\7\t\2\2\u00c8"+
		"\u00cb\3\2\2\2\u00c9\u00cb\5.\30\2\u00ca\u00c4\3\2\2\2\u00ca\u00c9\3\2"+
		"\2\2\u00cb\u00d1\3\2\2\2\u00cc\u00cd\f\5\2\2\u00cd\u00ce\7$\2\2\u00ce"+
		"\u00d0\5,\27\6\u00cf\u00cc\3\2\2\2\u00d0\u00d3\3\2\2\2\u00d1\u00cf\3\2"+
		"\2\2\u00d1\u00d2\3\2\2\2\u00d2-\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d4\u00d6"+
		"\5*\26\2\u00d5\u00d7\5(\25\2\u00d6\u00d5\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7"+
		"\u00e5\3\2\2\2\u00d8\u00da\5\30\r\2\u00d9\u00db\5(\25\2\u00da\u00d9\3"+
		"\2\2\2\u00da\u00db\3\2\2\2\u00db\u00e5\3\2\2\2\u00dc\u00e5\7\34\2\2\u00dd"+
		"\u00e5\7\37\2\2\u00de\u00e5\7 \2\2\u00df\u00e5\7!\2\2\u00e0\u00e1\7\26"+
		"\2\2\u00e1\u00e2\5$\23\2\u00e2\u00e3\7\27\2\2\u00e3\u00e5\3\2\2\2\u00e4"+
		"\u00d4\3\2\2\2\u00e4\u00d8\3\2\2\2\u00e4\u00dc\3\2\2\2\u00e4\u00dd\3\2"+
		"\2\2\u00e4\u00de\3\2\2\2\u00e4\u00df\3\2\2\2\u00e4\u00e0\3\2\2\2\u00e5"+
		"/\3\2\2\2\35\63:ADLOUox{\u0081\u0085\u008a\u008f\u0095\u0099\u00a8\u00ad"+
		"\u00b2\u00b7\u00bd\u00c2\u00ca\u00d1\u00d6\u00da\u00e4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}