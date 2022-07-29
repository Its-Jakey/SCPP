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
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		STRING=32, LIBRARY=33, ID=34, INT=35, HEX=36, BIN=37, VARIABLE_MODIFIER=38, 
		VARIABLE_SINGLE_MODIFIER=39, OPERATOR=40, WS=41, SINGLE_COMMENT=42, BLOCK_COMMENT=43;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_bracketStatement = 2, RULE_namespaceDeclaration = 3, 
		RULE_functionDeclaration = 4, RULE_ifStatement = 5, RULE_whileLoop = 6, 
		RULE_forLoop = 7, RULE_switchStatement = 8, RULE_caseStatement = 9, RULE_defaultStatement = 10, 
		RULE_nonBracketStatement = 11, RULE_variableDeclaration = 12, RULE_variableValueChange = 13, 
		RULE_functionCall = 14, RULE_returnStatement = 15, RULE_directive = 16, 
		RULE_defineDirective = 17, RULE_includeDirective = 18, RULE_codeBlock = 19, 
		RULE_argumentArray = 20, RULE_functionArgumentArray = 21, RULE_arrayIndex = 22, 
		RULE_variable = 23, RULE_expression = 24, RULE_value = 25, RULE_idList = 26;
	public static final String[] ruleNames = {
		"program", "statement", "bracketStatement", "namespaceDeclaration", "functionDeclaration", 
		"ifStatement", "whileLoop", "forLoop", "switchStatement", "caseStatement", 
		"defaultStatement", "nonBracketStatement", "variableDeclaration", "variableValueChange", 
		"functionCall", "returnStatement", "directive", "defineDirective", "includeDirective", 
		"codeBlock", "argumentArray", "functionArgumentArray", "arrayIndex", "variable", 
		"expression", "value", "idList"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "'public'", "'namespace'", "'uses'", "'('", "')'", "'is'", 
		"'inline'", "'func'", "'if'", "'while'", "'for'", "'from'", "'to'", "'by'", 
		"'switch'", "'{'", "'}'", "'case'", "'->'", "'default'", "'var'", "'='", 
		"'return'", "'#'", "'define'", "'include'", "','", "'['", "']'", "'::'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, "STRING", "LIBRARY", "ID", 
		"INT", "HEX", "BIN", "VARIABLE_MODIFIER", "VARIABLE_SINGLE_MODIFIER", 
		"OPERATOR", "WS", "SINGLE_COMMENT", "BLOCK_COMMENT"
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
			setState(55); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(54);
				statement();
				}
				}
				setState(57); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__15) | (1L << T__21) | (1L << T__23) | (1L << T__24) | (1L << ID))) != 0) );
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
			setState(64);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(59);
				bracketStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				nonBracketStatement();
				setState(61);
				match(T__0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(63);
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
		public SwitchStatementContext switchStatement() {
			return getRuleContext(SwitchStatementContext.class,0);
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
			setState(72);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				namespaceDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				functionDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(68);
				ifStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(69);
				whileLoop();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(70);
				forLoop();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(71);
				switchStatement();
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
		public IdListContext idList() {
			return getRuleContext(IdListContext.class,0);
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
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(74);
				((NamespaceDeclarationContext)_localctx).pub = match(T__1);
				}
			}

			setState(77);
			match(T__2);
			setState(78);
			match(ID);
			setState(90);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__2:
			case T__3:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__15:
			case T__16:
			case T__21:
			case T__23:
			case T__24:
			case ID:
				{
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(79);
					match(T__3);
					setState(80);
					match(T__4);
					setState(81);
					idList();
					setState(82);
					match(T__5);
					}
				}

				setState(86);
				codeBlock();
				}
				break;
			case T__6:
				{
				setState(87);
				match(T__6);
				setState(88);
				match(ID);
				setState(89);
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
		public Token inline;
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
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(92);
				((FunctionDeclarationContext)_localctx).pub = match(T__1);
				}
			}

			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(95);
				((FunctionDeclarationContext)_localctx).inline = match(T__7);
				}
			}

			setState(98);
			match(T__8);
			setState(99);
			match(ID);
			setState(100);
			match(T__4);
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(101);
				functionArgumentArray();
				}
			}

			setState(104);
			match(T__5);
			setState(105);
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
			setState(107);
			match(T__9);
			setState(108);
			match(T__4);
			setState(109);
			expression(0);
			setState(110);
			match(T__5);
			setState(111);
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
			setState(113);
			match(T__10);
			setState(114);
			match(T__4);
			setState(115);
			expression(0);
			setState(116);
			match(T__5);
			setState(117);
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
			setState(119);
			match(T__11);
			setState(120);
			match(T__4);
			setState(121);
			match(ID);
			setState(122);
			match(T__12);
			setState(123);
			expression(0);
			setState(124);
			match(T__13);
			setState(125);
			expression(0);
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__14) {
				{
				setState(126);
				match(T__14);
				setState(127);
				expression(0);
				}
			}

			setState(130);
			match(T__5);
			setState(131);
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

	public static class SwitchStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<CaseStatementContext> caseStatement() {
			return getRuleContexts(CaseStatementContext.class);
		}
		public CaseStatementContext caseStatement(int i) {
			return getRuleContext(CaseStatementContext.class,i);
		}
		public DefaultStatementContext defaultStatement() {
			return getRuleContext(DefaultStatementContext.class,0);
		}
		public SwitchStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterSwitchStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitSwitchStatement(this);
		}
	}

	public final SwitchStatementContext switchStatement() throws RecognitionException {
		SwitchStatementContext _localctx = new SwitchStatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_switchStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(T__15);
			setState(134);
			match(T__4);
			setState(135);
			expression(0);
			setState(136);
			match(T__5);
			setState(137);
			match(T__16);
			setState(139); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(138);
				caseStatement();
				}
				}
				setState(141); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__18 );
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__20) {
				{
				setState(143);
				defaultStatement();
				}
			}

			setState(146);
			match(T__17);
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

	public static class CaseStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CodeBlockContext codeBlock() {
			return getRuleContext(CodeBlockContext.class,0);
		}
		public CaseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterCaseStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitCaseStatement(this);
		}
	}

	public final CaseStatementContext caseStatement() throws RecognitionException {
		CaseStatementContext _localctx = new CaseStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_caseStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(T__18);
			setState(149);
			expression(0);
			setState(150);
			match(T__19);
			setState(151);
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

	public static class DefaultStatementContext extends ParserRuleContext {
		public CodeBlockContext codeBlock() {
			return getRuleContext(CodeBlockContext.class,0);
		}
		public DefaultStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterDefaultStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitDefaultStatement(this);
		}
	}

	public final DefaultStatementContext defaultStatement() throws RecognitionException {
		DefaultStatementContext _localctx = new DefaultStatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_defaultStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(T__20);
			setState(154);
			match(T__19);
			setState(155);
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
		enterRule(_localctx, 22, RULE_nonBracketStatement);
		try {
			setState(161);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(157);
				variableDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(158);
				variableValueChange();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(159);
				functionCall();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(160);
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
		enterRule(_localctx, 24, RULE_variableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(163);
				((VariableDeclarationContext)_localctx).pub = match(T__1);
				}
			}

			setState(166);
			match(T__21);
			setState(167);
			match(ID);
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__22) {
				{
				setState(168);
				match(T__22);
				setState(169);
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
		enterRule(_localctx, 26, RULE_variableValueChange);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			variable();
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(173);
				arrayIndex();
				}
			}

			setState(179);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VARIABLE_SINGLE_MODIFIER:
				{
				setState(176);
				match(VARIABLE_SINGLE_MODIFIER);
				}
				break;
			case T__22:
			case VARIABLE_MODIFIER:
				{
				setState(177);
				_la = _input.LA(1);
				if ( !(_la==T__22 || _la==VARIABLE_MODIFIER) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(178);
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
		enterRule(_localctx, 28, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			variable();
			setState(182);
			match(T__4);
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__16) | (1L << STRING) | (1L << ID) | (1L << INT) | (1L << HEX) | (1L << BIN))) != 0)) {
				{
				setState(183);
				argumentArray();
				}
			}

			setState(186);
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
		enterRule(_localctx, 30, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(T__23);
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__16) | (1L << STRING) | (1L << ID) | (1L << INT) | (1L << HEX) | (1L << BIN))) != 0)) {
				{
				setState(189);
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
		enterRule(_localctx, 32, RULE_directive);
		try {
			setState(194);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(192);
				defineDirective();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(193);
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
		public TerminalNode HEX() { return getToken(SCPPParser.HEX, 0); }
		public TerminalNode BIN() { return getToken(SCPPParser.BIN, 0); }
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
		enterRule(_localctx, 34, RULE_defineDirective);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			match(T__24);
			setState(197);
			match(T__25);
			setState(198);
			match(ID);
			setState(199);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << HEX) | (1L << BIN))) != 0)) ) {
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
		enterRule(_localctx, 36, RULE_includeDirective);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(T__24);
			setState(202);
			match(T__26);
			setState(203);
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
		enterRule(_localctx, 38, RULE_codeBlock);
		try {
			int _alt;
			setState(214);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__16:
				enterOuterAlt(_localctx, 1);
				{
				setState(205);
				match(T__16);
				setState(209);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1+1 ) {
						{
						{
						setState(206);
						statement();
						}
						} 
					}
					setState(211);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				}
				setState(212);
				match(T__17);
				}
				break;
			case T__1:
			case T__2:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__15:
			case T__21:
			case T__23:
			case T__24:
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(213);
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
		enterRule(_localctx, 40, RULE_argumentArray);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			expression(0);
			setState(219);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__27) {
				{
				setState(217);
				match(T__27);
				setState(218);
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
		enterRule(_localctx, 42, RULE_functionArgumentArray);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			match(ID);
			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__27) {
				{
				setState(222);
				match(T__27);
				setState(223);
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
		enterRule(_localctx, 44, RULE_arrayIndex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			match(T__28);
			setState(227);
			expression(0);
			setState(228);
			match(T__29);
			setState(230);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(229);
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
		enterRule(_localctx, 46, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			match(ID);
			setState(235);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(233);
				match(T__30);
				setState(234);
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
		int _startState = 48;
		enterRecursionRule(_localctx, 48, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				{
				setState(238);
				match(T__4);
				setState(239);
				expression(0);
				setState(240);
				match(T__5);
				}
				break;
			case T__16:
			case STRING:
			case ID:
			case INT:
			case HEX:
			case BIN:
				{
				setState(242);
				value();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(250);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expression);
					setState(245);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(246);
					match(OPERATOR);
					setState(247);
					expression(4);
					}
					} 
				}
				setState(252);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
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
		enterRule(_localctx, 50, RULE_value);
		try {
			setState(269);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(253);
				variable();
				setState(255);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(254);
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
				setState(257);
				functionCall();
				setState(259);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
				case 1:
					{
					setState(258);
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
				setState(261);
				match(STRING);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(262);
				match(INT);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(263);
				match(HEX);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(264);
				match(BIN);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(265);
				match(T__16);
				setState(266);
				argumentArray();
				setState(267);
				match(T__17);
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

	public static class IdListContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SCPPParser.ID, 0); }
		public IdListContext idList() {
			return getRuleContext(IdListContext.class,0);
		}
		public IdListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterIdList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitIdList(this);
		}
	}

	public final IdListContext idList() throws RecognitionException {
		IdListContext _localctx = new IdListContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_idList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			match(ID);
			setState(274);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__27) {
				{
				setState(272);
				match(T__27);
				setState(273);
				idList();
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 24:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3-\u0117\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\3\2\6\2:\n\2\r\2\16\2;\3\3\3\3\3\3\3\3"+
		"\3\3\5\3C\n\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4K\n\4\3\5\5\5N\n\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\5\5W\n\5\3\5\3\5\3\5\3\5\5\5]\n\5\3\6\5\6`\n\6\3\6"+
		"\5\6c\n\6\3\6\3\6\3\6\3\6\5\6i\n\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u0083"+
		"\n\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\6\n\u008e\n\n\r\n\16\n\u008f"+
		"\3\n\5\n\u0093\n\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3"+
		"\r\3\r\3\r\3\r\5\r\u00a4\n\r\3\16\5\16\u00a7\n\16\3\16\3\16\3\16\3\16"+
		"\5\16\u00ad\n\16\3\17\3\17\5\17\u00b1\n\17\3\17\3\17\3\17\5\17\u00b6\n"+
		"\17\3\20\3\20\3\20\5\20\u00bb\n\20\3\20\3\20\3\21\3\21\5\21\u00c1\n\21"+
		"\3\22\3\22\5\22\u00c5\n\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\7\25\u00d2\n\25\f\25\16\25\u00d5\13\25\3\25\3\25\5\25\u00d9"+
		"\n\25\3\26\3\26\3\26\5\26\u00de\n\26\3\27\3\27\3\27\5\27\u00e3\n\27\3"+
		"\30\3\30\3\30\3\30\5\30\u00e9\n\30\3\31\3\31\3\31\5\31\u00ee\n\31\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\5\32\u00f6\n\32\3\32\3\32\3\32\7\32\u00fb\n"+
		"\32\f\32\16\32\u00fe\13\32\3\33\3\33\5\33\u0102\n\33\3\33\3\33\5\33\u0106"+
		"\n\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u0110\n\33\3\34\3\34"+
		"\3\34\5\34\u0115\n\34\3\34\3\u00d3\3\62\35\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"$&(*,.\60\62\64\66\2\5\4\2\31\31((\3\2%\'\3\2\"#\2\u0127"+
		"\29\3\2\2\2\4B\3\2\2\2\6J\3\2\2\2\bM\3\2\2\2\n_\3\2\2\2\fm\3\2\2\2\16"+
		"s\3\2\2\2\20y\3\2\2\2\22\u0087\3\2\2\2\24\u0096\3\2\2\2\26\u009b\3\2\2"+
		"\2\30\u00a3\3\2\2\2\32\u00a6\3\2\2\2\34\u00ae\3\2\2\2\36\u00b7\3\2\2\2"+
		" \u00be\3\2\2\2\"\u00c4\3\2\2\2$\u00c6\3\2\2\2&\u00cb\3\2\2\2(\u00d8\3"+
		"\2\2\2*\u00da\3\2\2\2,\u00df\3\2\2\2.\u00e4\3\2\2\2\60\u00ea\3\2\2\2\62"+
		"\u00f5\3\2\2\2\64\u010f\3\2\2\2\66\u0111\3\2\2\28:\5\4\3\298\3\2\2\2:"+
		";\3\2\2\2;9\3\2\2\2;<\3\2\2\2<\3\3\2\2\2=C\5\6\4\2>?\5\30\r\2?@\7\3\2"+
		"\2@C\3\2\2\2AC\5\"\22\2B=\3\2\2\2B>\3\2\2\2BA\3\2\2\2C\5\3\2\2\2DK\5\b"+
		"\5\2EK\5\n\6\2FK\5\f\7\2GK\5\16\b\2HK\5\20\t\2IK\5\22\n\2JD\3\2\2\2JE"+
		"\3\2\2\2JF\3\2\2\2JG\3\2\2\2JH\3\2\2\2JI\3\2\2\2K\7\3\2\2\2LN\7\4\2\2"+
		"ML\3\2\2\2MN\3\2\2\2NO\3\2\2\2OP\7\5\2\2P\\\7$\2\2QR\7\6\2\2RS\7\7\2\2"+
		"ST\5\66\34\2TU\7\b\2\2UW\3\2\2\2VQ\3\2\2\2VW\3\2\2\2WX\3\2\2\2X]\5(\25"+
		"\2YZ\7\t\2\2Z[\7$\2\2[]\7\3\2\2\\V\3\2\2\2\\Y\3\2\2\2]\t\3\2\2\2^`\7\4"+
		"\2\2_^\3\2\2\2_`\3\2\2\2`b\3\2\2\2ac\7\n\2\2ba\3\2\2\2bc\3\2\2\2cd\3\2"+
		"\2\2de\7\13\2\2ef\7$\2\2fh\7\7\2\2gi\5,\27\2hg\3\2\2\2hi\3\2\2\2ij\3\2"+
		"\2\2jk\7\b\2\2kl\5(\25\2l\13\3\2\2\2mn\7\f\2\2no\7\7\2\2op\5\62\32\2p"+
		"q\7\b\2\2qr\5(\25\2r\r\3\2\2\2st\7\r\2\2tu\7\7\2\2uv\5\62\32\2vw\7\b\2"+
		"\2wx\5(\25\2x\17\3\2\2\2yz\7\16\2\2z{\7\7\2\2{|\7$\2\2|}\7\17\2\2}~\5"+
		"\62\32\2~\177\7\20\2\2\177\u0082\5\62\32\2\u0080\u0081\7\21\2\2\u0081"+
		"\u0083\5\62\32\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0084\3"+
		"\2\2\2\u0084\u0085\7\b\2\2\u0085\u0086\5(\25\2\u0086\21\3\2\2\2\u0087"+
		"\u0088\7\22\2\2\u0088\u0089\7\7\2\2\u0089\u008a\5\62\32\2\u008a\u008b"+
		"\7\b\2\2\u008b\u008d\7\23\2\2\u008c\u008e\5\24\13\2\u008d\u008c\3\2\2"+
		"\2\u008e\u008f\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0092"+
		"\3\2\2\2\u0091\u0093\5\26\f\2\u0092\u0091\3\2\2\2\u0092\u0093\3\2\2\2"+
		"\u0093\u0094\3\2\2\2\u0094\u0095\7\24\2\2\u0095\23\3\2\2\2\u0096\u0097"+
		"\7\25\2\2\u0097\u0098\5\62\32\2\u0098\u0099\7\26\2\2\u0099\u009a\5(\25"+
		"\2\u009a\25\3\2\2\2\u009b\u009c\7\27\2\2\u009c\u009d\7\26\2\2\u009d\u009e"+
		"\5(\25\2\u009e\27\3\2\2\2\u009f\u00a4\5\32\16\2\u00a0\u00a4\5\34\17\2"+
		"\u00a1\u00a4\5\36\20\2\u00a2\u00a4\5 \21\2\u00a3\u009f\3\2\2\2\u00a3\u00a0"+
		"\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a2\3\2\2\2\u00a4\31\3\2\2\2\u00a5"+
		"\u00a7\7\4\2\2\u00a6\u00a5\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\3\2"+
		"\2\2\u00a8\u00a9\7\30\2\2\u00a9\u00ac\7$\2\2\u00aa\u00ab\7\31\2\2\u00ab"+
		"\u00ad\5\62\32\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\33\3\2"+
		"\2\2\u00ae\u00b0\5\60\31\2\u00af\u00b1\5.\30\2\u00b0\u00af\3\2\2\2\u00b0"+
		"\u00b1\3\2\2\2\u00b1\u00b5\3\2\2\2\u00b2\u00b6\7)\2\2\u00b3\u00b4\t\2"+
		"\2\2\u00b4\u00b6\5\62\32\2\u00b5\u00b2\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6"+
		"\35\3\2\2\2\u00b7\u00b8\5\60\31\2\u00b8\u00ba\7\7\2\2\u00b9\u00bb\5*\26"+
		"\2\u00ba\u00b9\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd"+
		"\7\b\2\2\u00bd\37\3\2\2\2\u00be\u00c0\7\32\2\2\u00bf\u00c1\5\62\32\2\u00c0"+
		"\u00bf\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1!\3\2\2\2\u00c2\u00c5\5$\23\2"+
		"\u00c3\u00c5\5&\24\2\u00c4\u00c2\3\2\2\2\u00c4\u00c3\3\2\2\2\u00c5#\3"+
		"\2\2\2\u00c6\u00c7\7\33\2\2\u00c7\u00c8\7\34\2\2\u00c8\u00c9\7$\2\2\u00c9"+
		"\u00ca\t\3\2\2\u00ca%\3\2\2\2\u00cb\u00cc\7\33\2\2\u00cc\u00cd\7\35\2"+
		"\2\u00cd\u00ce\t\4\2\2\u00ce\'\3\2\2\2\u00cf\u00d3\7\23\2\2\u00d0\u00d2"+
		"\5\4\3\2\u00d1\u00d0\3\2\2\2\u00d2\u00d5\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d3"+
		"\u00d1\3\2\2\2\u00d4\u00d6\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d6\u00d9\7\24"+
		"\2\2\u00d7\u00d9\5\4\3\2\u00d8\u00cf\3\2\2\2\u00d8\u00d7\3\2\2\2\u00d9"+
		")\3\2\2\2\u00da\u00dd\5\62\32\2\u00db\u00dc\7\36\2\2\u00dc\u00de\5*\26"+
		"\2\u00dd\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de+\3\2\2\2\u00df\u00e2"+
		"\7$\2\2\u00e0\u00e1\7\36\2\2\u00e1\u00e3\5,\27\2\u00e2\u00e0\3\2\2\2\u00e2"+
		"\u00e3\3\2\2\2\u00e3-\3\2\2\2\u00e4\u00e5\7\37\2\2\u00e5\u00e6\5\62\32"+
		"\2\u00e6\u00e8\7 \2\2\u00e7\u00e9\5.\30\2\u00e8\u00e7\3\2\2\2\u00e8\u00e9"+
		"\3\2\2\2\u00e9/\3\2\2\2\u00ea\u00ed\7$\2\2\u00eb\u00ec\7!\2\2\u00ec\u00ee"+
		"\5\60\31\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\61\3\2\2\2\u00ef"+
		"\u00f0\b\32\1\2\u00f0\u00f1\7\7\2\2\u00f1\u00f2\5\62\32\2\u00f2\u00f3"+
		"\7\b\2\2\u00f3\u00f6\3\2\2\2\u00f4\u00f6\5\64\33\2\u00f5\u00ef\3\2\2\2"+
		"\u00f5\u00f4\3\2\2\2\u00f6\u00fc\3\2\2\2\u00f7\u00f8\f\5\2\2\u00f8\u00f9"+
		"\7*\2\2\u00f9\u00fb\5\62\32\6\u00fa\u00f7\3\2\2\2\u00fb\u00fe\3\2\2\2"+
		"\u00fc\u00fa\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\63\3\2\2\2\u00fe\u00fc"+
		"\3\2\2\2\u00ff\u0101\5\60\31\2\u0100\u0102\5.\30\2\u0101\u0100\3\2\2\2"+
		"\u0101\u0102\3\2\2\2\u0102\u0110\3\2\2\2\u0103\u0105\5\36\20\2\u0104\u0106"+
		"\5.\30\2\u0105\u0104\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0110\3\2\2\2\u0107"+
		"\u0110\7\"\2\2\u0108\u0110\7%\2\2\u0109\u0110\7&\2\2\u010a\u0110\7\'\2"+
		"\2\u010b\u010c\7\23\2\2\u010c\u010d\5*\26\2\u010d\u010e\7\24\2\2\u010e"+
		"\u0110\3\2\2\2\u010f\u00ff\3\2\2\2\u010f\u0103\3\2\2\2\u010f\u0107\3\2"+
		"\2\2\u010f\u0108\3\2\2\2\u010f\u0109\3\2\2\2\u010f\u010a\3\2\2\2\u010f"+
		"\u010b\3\2\2\2\u0110\65\3\2\2\2\u0111\u0114\7$\2\2\u0112\u0113\7\36\2"+
		"\2\u0113\u0115\5\66\34\2\u0114\u0112\3\2\2\2\u0114\u0115\3\2\2\2\u0115"+
		"\67\3\2\2\2\";BJMV\\_bh\u0082\u008f\u0092\u00a3\u00a6\u00ac\u00b0\u00b5"+
		"\u00ba\u00c0\u00c4\u00d3\u00d8\u00dd\u00e2\u00e8\u00ed\u00f5\u00fc\u0101"+
		"\u0105\u010f\u0114";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}