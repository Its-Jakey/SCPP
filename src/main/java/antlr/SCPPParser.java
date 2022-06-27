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
		STRING=25, LIBRARY=26, ID=27, INT=28, HEX=29, BIN=30, VARIABLE_MODIFIER=31, 
		VARIABLE_SINGLE_MODIFIER=32, OPERATOR=33, WS=34, SINGLE_COMMENT=35, BLOCK_COMMENT=36;
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
		null, "';'", "'public'", "'namespace'", "'func'", "'('", "')'", "'if'", 
		"'while'", "'for'", "'from'", "'to'", "'by'", "'var'", "'='", "'return'", 
		"'#'", "'define'", "'include'", "'{'", "'}'", "','", "'['", "']'", "'::'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "STRING", "LIBRARY", "ID", "INT", "HEX", "BIN", "VARIABLE_MODIFIER", 
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
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__12) | (1L << T__14) | (1L << T__15) | (1L << ID))) != 0) );
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
		public TerminalNode ID() { return getToken(SCPPParser.ID, 0); }
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
			setState(70);
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
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(72);
				((FunctionDeclarationContext)_localctx).pub = match(T__1);
				}
			}

			setState(75);
			match(T__3);
			setState(76);
			match(ID);
			setState(77);
			match(T__4);
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(78);
				functionArgumentArray();
				}
			}

			setState(81);
			match(T__5);
			setState(82);
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
			setState(84);
			match(T__6);
			setState(85);
			match(T__4);
			setState(86);
			expression(0);
			setState(87);
			match(T__5);
			setState(88);
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
			setState(90);
			match(T__7);
			setState(91);
			match(T__4);
			setState(92);
			expression(0);
			setState(93);
			match(T__5);
			setState(94);
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
			setState(96);
			match(T__8);
			setState(97);
			match(T__4);
			setState(98);
			match(ID);
			setState(99);
			match(T__9);
			setState(100);
			expression(0);
			setState(101);
			match(T__10);
			setState(102);
			expression(0);
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(103);
				match(T__11);
				setState(104);
				expression(0);
				}
			}

			setState(107);
			match(T__5);
			setState(108);
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
			setState(114);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(110);
				variableDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(111);
				variableValueChange();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(112);
				functionCall();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(113);
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
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(116);
				((VariableDeclarationContext)_localctx).pub = match(T__1);
				}
			}

			setState(119);
			match(T__12);
			setState(120);
			match(ID);
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(121);
				match(T__13);
				setState(122);
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
			setState(125);
			variable();
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(126);
				arrayIndex();
				}
			}

			setState(132);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VARIABLE_SINGLE_MODIFIER:
				{
				setState(129);
				match(VARIABLE_SINGLE_MODIFIER);
				}
				break;
			case T__13:
			case VARIABLE_MODIFIER:
				{
				setState(130);
				_la = _input.LA(1);
				if ( !(_la==T__13 || _la==VARIABLE_MODIFIER) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(131);
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
			setState(134);
			variable();
			setState(135);
			match(T__4);
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__18) | (1L << STRING) | (1L << ID) | (1L << INT) | (1L << HEX) | (1L << BIN))) != 0)) {
				{
				setState(136);
				argumentArray();
				}
			}

			setState(139);
			match(T__5);
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
			setState(141);
			match(T__14);
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__18) | (1L << STRING) | (1L << ID) | (1L << INT) | (1L << HEX) | (1L << BIN))) != 0)) {
				{
				setState(142);
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
			setState(147);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(145);
				defineDirective();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(146);
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
			setState(149);
			match(T__15);
			setState(150);
			match(T__16);
			setState(151);
			match(ID);
			setState(152);
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
			setState(154);
			match(T__15);
			setState(155);
			match(T__17);
			setState(156);
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
			setState(167);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__18:
				enterOuterAlt(_localctx, 1);
				{
				setState(158);
				match(T__18);
				setState(162);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1+1 ) {
						{
						{
						setState(159);
						statement();
						}
						} 
					}
					setState(164);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				}
				setState(165);
				match(T__19);
				}
				break;
			case T__1:
			case T__2:
			case T__3:
			case T__6:
			case T__7:
			case T__8:
			case T__12:
			case T__14:
			case T__15:
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(166);
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
			setState(169);
			expression(0);
			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__20) {
				{
				setState(170);
				match(T__20);
				setState(171);
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
			setState(174);
			match(ID);
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__20) {
				{
				setState(175);
				match(T__20);
				setState(176);
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
			setState(179);
			match(T__21);
			setState(180);
			expression(0);
			setState(181);
			match(T__22);
			setState(183);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(182);
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
			setState(185);
			match(ID);
			setState(188);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(186);
				match(T__23);
				setState(187);
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
			setState(196);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				{
				setState(191);
				match(T__4);
				setState(192);
				expression(0);
				setState(193);
				match(T__5);
				}
				break;
			case T__18:
			case STRING:
			case ID:
			case INT:
			case HEX:
			case BIN:
				{
				setState(195);
				value();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(203);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expression);
					setState(198);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(199);
					match(OPERATOR);
					setState(200);
					expression(4);
					}
					} 
				}
				setState(205);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
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
			setState(222);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(206);
				variable();
				setState(208);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					{
					setState(207);
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
				setState(210);
				functionCall();
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
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(214);
				match(STRING);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(215);
				match(INT);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(216);
				match(HEX);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(217);
				match(BIN);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(218);
				match(T__18);
				setState(219);
				argumentArray();
				setState(220);
				match(T__19);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3&\u00e3\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\6\2\62"+
		"\n\2\r\2\16\2\63\3\3\3\3\3\3\3\3\3\3\5\3;\n\3\3\4\3\4\3\4\3\4\3\4\5\4"+
		"B\n\4\3\5\5\5E\n\5\3\5\3\5\3\5\3\5\3\6\5\6L\n\6\3\6\3\6\3\6\3\6\5\6R\n"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tl\n\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\5\n"+
		"u\n\n\3\13\5\13x\n\13\3\13\3\13\3\13\3\13\5\13~\n\13\3\f\3\f\5\f\u0082"+
		"\n\f\3\f\3\f\3\f\5\f\u0087\n\f\3\r\3\r\3\r\5\r\u008c\n\r\3\r\3\r\3\16"+
		"\3\16\5\16\u0092\n\16\3\17\3\17\5\17\u0096\n\17\3\20\3\20\3\20\3\20\3"+
		"\20\3\21\3\21\3\21\3\21\3\22\3\22\7\22\u00a3\n\22\f\22\16\22\u00a6\13"+
		"\22\3\22\3\22\5\22\u00aa\n\22\3\23\3\23\3\23\5\23\u00af\n\23\3\24\3\24"+
		"\3\24\5\24\u00b4\n\24\3\25\3\25\3\25\3\25\5\25\u00ba\n\25\3\26\3\26\3"+
		"\26\5\26\u00bf\n\26\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u00c7\n\27\3\27"+
		"\3\27\3\27\7\27\u00cc\n\27\f\27\16\27\u00cf\13\27\3\30\3\30\5\30\u00d3"+
		"\n\30\3\30\3\30\5\30\u00d7\n\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\5\30\u00e1\n\30\3\30\3\u00a4\3,\31\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$&(*,.\2\4\4\2\20\20!!\3\2\33\34\2\u00f0\2\61\3\2\2\2\4:\3\2\2\2"+
		"\6A\3\2\2\2\bD\3\2\2\2\nK\3\2\2\2\fV\3\2\2\2\16\\\3\2\2\2\20b\3\2\2\2"+
		"\22t\3\2\2\2\24w\3\2\2\2\26\177\3\2\2\2\30\u0088\3\2\2\2\32\u008f\3\2"+
		"\2\2\34\u0095\3\2\2\2\36\u0097\3\2\2\2 \u009c\3\2\2\2\"\u00a9\3\2\2\2"+
		"$\u00ab\3\2\2\2&\u00b0\3\2\2\2(\u00b5\3\2\2\2*\u00bb\3\2\2\2,\u00c6\3"+
		"\2\2\2.\u00e0\3\2\2\2\60\62\5\4\3\2\61\60\3\2\2\2\62\63\3\2\2\2\63\61"+
		"\3\2\2\2\63\64\3\2\2\2\64\3\3\2\2\2\65;\5\6\4\2\66\67\5\22\n\2\678\7\3"+
		"\2\28;\3\2\2\29;\5\34\17\2:\65\3\2\2\2:\66\3\2\2\2:9\3\2\2\2;\5\3\2\2"+
		"\2<B\5\b\5\2=B\5\n\6\2>B\5\f\7\2?B\5\16\b\2@B\5\20\t\2A<\3\2\2\2A=\3\2"+
		"\2\2A>\3\2\2\2A?\3\2\2\2A@\3\2\2\2B\7\3\2\2\2CE\7\4\2\2DC\3\2\2\2DE\3"+
		"\2\2\2EF\3\2\2\2FG\7\5\2\2GH\7\35\2\2HI\5\"\22\2I\t\3\2\2\2JL\7\4\2\2"+
		"KJ\3\2\2\2KL\3\2\2\2LM\3\2\2\2MN\7\6\2\2NO\7\35\2\2OQ\7\7\2\2PR\5&\24"+
		"\2QP\3\2\2\2QR\3\2\2\2RS\3\2\2\2ST\7\b\2\2TU\5\"\22\2U\13\3\2\2\2VW\7"+
		"\t\2\2WX\7\7\2\2XY\5,\27\2YZ\7\b\2\2Z[\5\"\22\2[\r\3\2\2\2\\]\7\n\2\2"+
		"]^\7\7\2\2^_\5,\27\2_`\7\b\2\2`a\5\"\22\2a\17\3\2\2\2bc\7\13\2\2cd\7\7"+
		"\2\2de\7\35\2\2ef\7\f\2\2fg\5,\27\2gh\7\r\2\2hk\5,\27\2ij\7\16\2\2jl\5"+
		",\27\2ki\3\2\2\2kl\3\2\2\2lm\3\2\2\2mn\7\b\2\2no\5\"\22\2o\21\3\2\2\2"+
		"pu\5\24\13\2qu\5\26\f\2ru\5\30\r\2su\5\32\16\2tp\3\2\2\2tq\3\2\2\2tr\3"+
		"\2\2\2ts\3\2\2\2u\23\3\2\2\2vx\7\4\2\2wv\3\2\2\2wx\3\2\2\2xy\3\2\2\2y"+
		"z\7\17\2\2z}\7\35\2\2{|\7\20\2\2|~\5,\27\2}{\3\2\2\2}~\3\2\2\2~\25\3\2"+
		"\2\2\177\u0081\5*\26\2\u0080\u0082\5(\25\2\u0081\u0080\3\2\2\2\u0081\u0082"+
		"\3\2\2\2\u0082\u0086\3\2\2\2\u0083\u0087\7\"\2\2\u0084\u0085\t\2\2\2\u0085"+
		"\u0087\5,\27\2\u0086\u0083\3\2\2\2\u0086\u0084\3\2\2\2\u0087\27\3\2\2"+
		"\2\u0088\u0089\5*\26\2\u0089\u008b\7\7\2\2\u008a\u008c\5$\23\2\u008b\u008a"+
		"\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\7\b\2\2\u008e"+
		"\31\3\2\2\2\u008f\u0091\7\21\2\2\u0090\u0092\5,\27\2\u0091\u0090\3\2\2"+
		"\2\u0091\u0092\3\2\2\2\u0092\33\3\2\2\2\u0093\u0096\5\36\20\2\u0094\u0096"+
		"\5 \21\2\u0095\u0093\3\2\2\2\u0095\u0094\3\2\2\2\u0096\35\3\2\2\2\u0097"+
		"\u0098\7\22\2\2\u0098\u0099\7\23\2\2\u0099\u009a\7\35\2\2\u009a\u009b"+
		"\7\36\2\2\u009b\37\3\2\2\2\u009c\u009d\7\22\2\2\u009d\u009e\7\24\2\2\u009e"+
		"\u009f\t\3\2\2\u009f!\3\2\2\2\u00a0\u00a4\7\25\2\2\u00a1\u00a3\5\4\3\2"+
		"\u00a2\u00a1\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a4\u00a2"+
		"\3\2\2\2\u00a5\u00a7\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7\u00aa\7\26\2\2"+
		"\u00a8\u00aa\5\4\3\2\u00a9\u00a0\3\2\2\2\u00a9\u00a8\3\2\2\2\u00aa#\3"+
		"\2\2\2\u00ab\u00ae\5,\27\2\u00ac\u00ad\7\27\2\2\u00ad\u00af\5$\23\2\u00ae"+
		"\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af%\3\2\2\2\u00b0\u00b3\7\35\2\2"+
		"\u00b1\u00b2\7\27\2\2\u00b2\u00b4\5&\24\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4"+
		"\3\2\2\2\u00b4\'\3\2\2\2\u00b5\u00b6\7\30\2\2\u00b6\u00b7\5,\27\2\u00b7"+
		"\u00b9\7\31\2\2\u00b8\u00ba\5(\25\2\u00b9\u00b8\3\2\2\2\u00b9\u00ba\3"+
		"\2\2\2\u00ba)\3\2\2\2\u00bb\u00be\7\35\2\2\u00bc\u00bd\7\32\2\2\u00bd"+
		"\u00bf\5*\26\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf+\3\2\2\2"+
		"\u00c0\u00c1\b\27\1\2\u00c1\u00c2\7\7\2\2\u00c2\u00c3\5,\27\2\u00c3\u00c4"+
		"\7\b\2\2\u00c4\u00c7\3\2\2\2\u00c5\u00c7\5.\30\2\u00c6\u00c0\3\2\2\2\u00c6"+
		"\u00c5\3\2\2\2\u00c7\u00cd\3\2\2\2\u00c8\u00c9\f\5\2\2\u00c9\u00ca\7#"+
		"\2\2\u00ca\u00cc\5,\27\6\u00cb\u00c8\3\2\2\2\u00cc\u00cf\3\2\2\2\u00cd"+
		"\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce-\3\2\2\2\u00cf\u00cd\3\2\2\2"+
		"\u00d0\u00d2\5*\26\2\u00d1\u00d3\5(\25\2\u00d2\u00d1\3\2\2\2\u00d2\u00d3"+
		"\3\2\2\2\u00d3\u00e1\3\2\2\2\u00d4\u00d6\5\30\r\2\u00d5\u00d7\5(\25\2"+
		"\u00d6\u00d5\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00e1\3\2\2\2\u00d8\u00e1"+
		"\7\33\2\2\u00d9\u00e1\7\36\2\2\u00da\u00e1\7\37\2\2\u00db\u00e1\7 \2\2"+
		"\u00dc\u00dd\7\25\2\2\u00dd\u00de\5$\23\2\u00de\u00df\7\26\2\2\u00df\u00e1"+
		"\3\2\2\2\u00e0\u00d0\3\2\2\2\u00e0\u00d4\3\2\2\2\u00e0\u00d8\3\2\2\2\u00e0"+
		"\u00d9\3\2\2\2\u00e0\u00da\3\2\2\2\u00e0\u00db\3\2\2\2\u00e0\u00dc\3\2"+
		"\2\2\u00e1/\3\2\2\2\34\63:ADKQktw}\u0081\u0086\u008b\u0091\u0095\u00a4"+
		"\u00a9\u00ae\u00b3\u00b9\u00be\u00c6\u00cd\u00d2\u00d6\u00e0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}