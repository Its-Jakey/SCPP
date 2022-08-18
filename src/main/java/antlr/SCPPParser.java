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
		T__31=32, T__32=33, T__33=34, T__34=35, STRING=36, LIBRARY=37, ID=38, 
		INT=39, HEX=40, BIN=41, VARIABLE_MODIFIER=42, VARIABLE_SINGLE_MODIFIER=43, 
		OPERATOR=44, WS=45, SINGLE_COMMENT=46, BLOCK_COMMENT=47;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_bracketStatement = 2, RULE_namespaceDeclaration = 3, 
		RULE_functionDeclaration = 4, RULE_ifStatement = 5, RULE_ifPart = 6, RULE_elsePart = 7, 
		RULE_whileLoop = 8, RULE_forLoop = 9, RULE_switchStatement = 10, RULE_caseStatement = 11, 
		RULE_defaultStatement = 12, RULE_nonBracketStatement = 13, RULE_variableDeclaration = 14, 
		RULE_variableValueChange = 15, RULE_functionCall = 16, RULE_returnStatement = 17, 
		RULE_directive = 18, RULE_defineDirective = 19, RULE_includeDirective = 20, 
		RULE_codeBlock = 21, RULE_argumentArray = 22, RULE_functionArgumentArray = 23, 
		RULE_arrayIndex = 24, RULE_variable = 25, RULE_expression = 26, RULE_value = 27, 
		RULE_conditionalValue = 28, RULE_idList = 29;
	public static final String[] ruleNames = {
		"program", "statement", "bracketStatement", "namespaceDeclaration", "functionDeclaration", 
		"ifStatement", "ifPart", "elsePart", "whileLoop", "forLoop", "switchStatement", 
		"caseStatement", "defaultStatement", "nonBracketStatement", "variableDeclaration", 
		"variableValueChange", "functionCall", "returnStatement", "directive", 
		"defineDirective", "includeDirective", "codeBlock", "argumentArray", "functionArgumentArray", 
		"arrayIndex", "variable", "expression", "value", "conditionalValue", "idList"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "'public'", "'namespace'", "'uses'", "'('", "')'", "'is'", 
		"'inline'", "'func'", "'if'", "'else'", "'while'", "'for'", "'from'", 
		"'to'", "'by'", "'switch'", "'{'", "'}'", "'case'", "'->'", "'default'", 
		"'var'", "'='", "'return'", "'#'", "'define'", "'include'", "','", "'['", 
		"']'", "'::'", "'?'", "':'", "'!'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"STRING", "LIBRARY", "ID", "INT", "HEX", "BIN", "VARIABLE_MODIFIER", "VARIABLE_SINGLE_MODIFIER", 
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
			setState(61); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(60);
				statement();
				}
				}
				setState(63); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << T__16) | (1L << T__22) | (1L << T__24) | (1L << T__25) | (1L << ID))) != 0) );
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
			setState(70);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				bracketStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				nonBracketStatement();
				setState(67);
				match(T__0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(69);
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
			setState(78);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(72);
				namespaceDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
				functionDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(74);
				ifStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(75);
				whileLoop();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(76);
				forLoop();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(77);
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
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(80);
				((NamespaceDeclarationContext)_localctx).pub = match(T__1);
				}
			}

			setState(83);
			match(T__2);
			setState(84);
			match(ID);
			setState(96);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__2:
			case T__3:
			case T__7:
			case T__8:
			case T__9:
			case T__11:
			case T__12:
			case T__16:
			case T__17:
			case T__22:
			case T__24:
			case T__25:
			case ID:
				{
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(85);
					match(T__3);
					setState(86);
					match(T__4);
					setState(87);
					idList();
					setState(88);
					match(T__5);
					}
				}

				setState(92);
				codeBlock();
				}
				break;
			case T__6:
				{
				setState(93);
				match(T__6);
				setState(94);
				match(ID);
				setState(95);
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
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(98);
				((FunctionDeclarationContext)_localctx).pub = match(T__1);
				}
			}

			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(101);
				((FunctionDeclarationContext)_localctx).inline = match(T__7);
				}
			}

			setState(104);
			match(T__8);
			setState(105);
			match(ID);
			setState(106);
			match(T__4);
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(107);
				functionArgumentArray();
				}
			}

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

	public static class IfStatementContext extends ParserRuleContext {
		public IfPartContext ifPart() {
			return getRuleContext(IfPartContext.class,0);
		}
		public ElsePartContext elsePart() {
			return getRuleContext(ElsePartContext.class,0);
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
			setState(113);
			ifPart();
			setState(115);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(114);
				elsePart();
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

	public static class IfPartContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CodeBlockContext codeBlock() {
			return getRuleContext(CodeBlockContext.class,0);
		}
		public IfPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifPart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterIfPart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitIfPart(this);
		}
	}

	public final IfPartContext ifPart() throws RecognitionException {
		IfPartContext _localctx = new IfPartContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_ifPart);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(T__9);
			setState(118);
			match(T__4);
			setState(119);
			expression(0);
			setState(120);
			match(T__5);
			setState(121);
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

	public static class ElsePartContext extends ParserRuleContext {
		public CodeBlockContext codeBlock() {
			return getRuleContext(CodeBlockContext.class,0);
		}
		public ElsePartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elsePart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterElsePart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitElsePart(this);
		}
	}

	public final ElsePartContext elsePart() throws RecognitionException {
		ElsePartContext _localctx = new ElsePartContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_elsePart);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(T__10);
			setState(124);
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
		enterRule(_localctx, 16, RULE_whileLoop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(T__11);
			setState(127);
			match(T__4);
			setState(128);
			expression(0);
			setState(129);
			match(T__5);
			setState(130);
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
		enterRule(_localctx, 18, RULE_forLoop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(T__12);
			setState(133);
			match(T__4);
			setState(134);
			match(ID);
			setState(135);
			match(T__13);
			setState(136);
			expression(0);
			setState(137);
			match(T__14);
			setState(138);
			expression(0);
			setState(141);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__15) {
				{
				setState(139);
				match(T__15);
				setState(140);
				expression(0);
				}
			}

			setState(143);
			match(T__5);
			setState(144);
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
		enterRule(_localctx, 20, RULE_switchStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(T__16);
			setState(147);
			match(T__4);
			setState(148);
			expression(0);
			setState(149);
			match(T__5);
			setState(150);
			match(T__17);
			setState(152); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(151);
				caseStatement();
				}
				}
				setState(154); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__19 );
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(156);
				defaultStatement();
				}
			}

			setState(159);
			match(T__18);
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
		enterRule(_localctx, 22, RULE_caseStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(T__19);
			setState(162);
			expression(0);
			setState(163);
			match(T__20);
			setState(164);
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
		enterRule(_localctx, 24, RULE_defaultStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(T__21);
			setState(167);
			match(T__20);
			setState(168);
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
		enterRule(_localctx, 26, RULE_nonBracketStatement);
		try {
			setState(174);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(170);
				variableDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(171);
				variableValueChange();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(172);
				functionCall();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(173);
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
		enterRule(_localctx, 28, RULE_variableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(176);
				((VariableDeclarationContext)_localctx).pub = match(T__1);
				}
			}

			setState(179);
			match(T__22);
			setState(180);
			match(ID);
			setState(183);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(181);
				match(T__23);
				setState(182);
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
		enterRule(_localctx, 30, RULE_variableValueChange);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			variable();
			setState(187);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__29) {
				{
				setState(186);
				arrayIndex();
				}
			}

			setState(192);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VARIABLE_SINGLE_MODIFIER:
				{
				setState(189);
				match(VARIABLE_SINGLE_MODIFIER);
				}
				break;
			case T__23:
			case VARIABLE_MODIFIER:
				{
				setState(190);
				_la = _input.LA(1);
				if ( !(_la==T__23 || _la==VARIABLE_MODIFIER) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(191);
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
		enterRule(_localctx, 32, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			variable();
			setState(195);
			match(T__4);
			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__17) | (1L << T__32) | (1L << STRING) | (1L << ID) | (1L << INT) | (1L << HEX) | (1L << BIN))) != 0)) {
				{
				setState(196);
				argumentArray();
				}
			}

			setState(199);
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
		enterRule(_localctx, 34, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(T__24);
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__17) | (1L << T__32) | (1L << STRING) | (1L << ID) | (1L << INT) | (1L << HEX) | (1L << BIN))) != 0)) {
				{
				setState(202);
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
		enterRule(_localctx, 36, RULE_directive);
		try {
			setState(207);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(205);
				defineDirective();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(206);
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
		enterRule(_localctx, 38, RULE_defineDirective);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			match(T__25);
			setState(210);
			match(T__26);
			setState(211);
			match(ID);
			setState(212);
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
		enterRule(_localctx, 40, RULE_includeDirective);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			match(T__25);
			setState(215);
			match(T__27);
			setState(216);
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
		enterRule(_localctx, 42, RULE_codeBlock);
		try {
			int _alt;
			setState(227);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__17:
				enterOuterAlt(_localctx, 1);
				{
				setState(218);
				match(T__17);
				setState(222);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1+1 ) {
						{
						{
						setState(219);
						statement();
						}
						} 
					}
					setState(224);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				}
				setState(225);
				match(T__18);
				}
				break;
			case T__1:
			case T__2:
			case T__7:
			case T__8:
			case T__9:
			case T__11:
			case T__12:
			case T__16:
			case T__22:
			case T__24:
			case T__25:
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(226);
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
		enterRule(_localctx, 44, RULE_argumentArray);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			expression(0);
			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(230);
				match(T__28);
				setState(231);
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
		enterRule(_localctx, 46, RULE_functionArgumentArray);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			match(ID);
			setState(237);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(235);
				match(T__28);
				setState(236);
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
		enterRule(_localctx, 48, RULE_arrayIndex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			match(T__29);
			setState(240);
			expression(0);
			setState(241);
			match(T__30);
			setState(243);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(242);
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
		enterRule(_localctx, 50, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			match(ID);
			setState(248);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				setState(246);
				match(T__31);
				setState(247);
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
		int _startState = 52;
		enterRecursionRule(_localctx, 52, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				{
				setState(251);
				match(T__4);
				setState(252);
				expression(0);
				setState(253);
				match(T__5);
				}
				break;
			case T__17:
			case T__32:
			case STRING:
			case ID:
			case INT:
			case HEX:
			case BIN:
				{
				setState(255);
				value();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(263);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expression);
					setState(258);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(259);
					match(OPERATOR);
					setState(260);
					expression(4);
					}
					} 
				}
				setState(265);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
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
		public ConditionalValueContext conditionalValue() {
			return getRuleContext(ConditionalValueContext.class,0);
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
		enterRule(_localctx, 54, RULE_value);
		try {
			setState(283);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(266);
				variable();
				setState(268);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
				case 1:
					{
					setState(267);
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
				setState(270);
				functionCall();
				setState(272);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(271);
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
				setState(274);
				conditionalValue();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(275);
				match(STRING);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(276);
				match(INT);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(277);
				match(HEX);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(278);
				match(BIN);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(279);
				match(T__17);
				setState(280);
				argumentArray();
				setState(281);
				match(T__18);
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

	public static class ConditionalValueContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ConditionalValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).enterConditionalValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SCPPListener) ((SCPPListener)listener).exitConditionalValue(this);
		}
	}

	public final ConditionalValueContext conditionalValue() throws RecognitionException {
		ConditionalValueContext _localctx = new ConditionalValueContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_conditionalValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			match(T__32);
			setState(286);
			expression(0);
			setState(287);
			match(T__33);
			setState(288);
			expression(0);
			setState(289);
			match(T__34);
			setState(290);
			expression(0);
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
		enterRule(_localctx, 58, RULE_idList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			match(ID);
			setState(295);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(293);
				match(T__28);
				setState(294);
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
		case 26:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\61\u012c\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\6\2@"+
		"\n\2\r\2\16\2A\3\3\3\3\3\3\3\3\3\3\5\3I\n\3\3\4\3\4\3\4\3\4\3\4\3\4\5"+
		"\4Q\n\4\3\5\5\5T\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5]\n\5\3\5\3\5\3\5"+
		"\3\5\5\5c\n\5\3\6\5\6f\n\6\3\6\5\6i\n\6\3\6\3\6\3\6\3\6\5\6o\n\6\3\6\3"+
		"\6\3\6\3\7\3\7\5\7v\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u0090"+
		"\n\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u009b\n\f\r\f\16\f\u009c"+
		"\3\f\5\f\u00a0\n\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\5\17\u00b1\n\17\3\20\5\20\u00b4\n\20\3\20\3\20\3\20\3"+
		"\20\5\20\u00ba\n\20\3\21\3\21\5\21\u00be\n\21\3\21\3\21\3\21\5\21\u00c3"+
		"\n\21\3\22\3\22\3\22\5\22\u00c8\n\22\3\22\3\22\3\23\3\23\5\23\u00ce\n"+
		"\23\3\24\3\24\5\24\u00d2\n\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26"+
		"\3\26\3\27\3\27\7\27\u00df\n\27\f\27\16\27\u00e2\13\27\3\27\3\27\5\27"+
		"\u00e6\n\27\3\30\3\30\3\30\5\30\u00eb\n\30\3\31\3\31\3\31\5\31\u00f0\n"+
		"\31\3\32\3\32\3\32\3\32\5\32\u00f6\n\32\3\33\3\33\3\33\5\33\u00fb\n\33"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\5\34\u0103\n\34\3\34\3\34\3\34\7\34\u0108"+
		"\n\34\f\34\16\34\u010b\13\34\3\35\3\35\5\35\u010f\n\35\3\35\3\35\5\35"+
		"\u0113\n\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u011e\n"+
		"\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\5\37\u012a\n\37"+
		"\3\37\3\u00e0\3\66 \2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60"+
		"\62\64\668:<\2\5\4\2\32\32,,\3\2)+\3\2&\'\2\u013b\2?\3\2\2\2\4H\3\2\2"+
		"\2\6P\3\2\2\2\bS\3\2\2\2\ne\3\2\2\2\fs\3\2\2\2\16w\3\2\2\2\20}\3\2\2\2"+
		"\22\u0080\3\2\2\2\24\u0086\3\2\2\2\26\u0094\3\2\2\2\30\u00a3\3\2\2\2\32"+
		"\u00a8\3\2\2\2\34\u00b0\3\2\2\2\36\u00b3\3\2\2\2 \u00bb\3\2\2\2\"\u00c4"+
		"\3\2\2\2$\u00cb\3\2\2\2&\u00d1\3\2\2\2(\u00d3\3\2\2\2*\u00d8\3\2\2\2,"+
		"\u00e5\3\2\2\2.\u00e7\3\2\2\2\60\u00ec\3\2\2\2\62\u00f1\3\2\2\2\64\u00f7"+
		"\3\2\2\2\66\u0102\3\2\2\28\u011d\3\2\2\2:\u011f\3\2\2\2<\u0126\3\2\2\2"+
		">@\5\4\3\2?>\3\2\2\2@A\3\2\2\2A?\3\2\2\2AB\3\2\2\2B\3\3\2\2\2CI\5\6\4"+
		"\2DE\5\34\17\2EF\7\3\2\2FI\3\2\2\2GI\5&\24\2HC\3\2\2\2HD\3\2\2\2HG\3\2"+
		"\2\2I\5\3\2\2\2JQ\5\b\5\2KQ\5\n\6\2LQ\5\f\7\2MQ\5\22\n\2NQ\5\24\13\2O"+
		"Q\5\26\f\2PJ\3\2\2\2PK\3\2\2\2PL\3\2\2\2PM\3\2\2\2PN\3\2\2\2PO\3\2\2\2"+
		"Q\7\3\2\2\2RT\7\4\2\2SR\3\2\2\2ST\3\2\2\2TU\3\2\2\2UV\7\5\2\2Vb\7(\2\2"+
		"WX\7\6\2\2XY\7\7\2\2YZ\5<\37\2Z[\7\b\2\2[]\3\2\2\2\\W\3\2\2\2\\]\3\2\2"+
		"\2]^\3\2\2\2^c\5,\27\2_`\7\t\2\2`a\7(\2\2ac\7\3\2\2b\\\3\2\2\2b_\3\2\2"+
		"\2c\t\3\2\2\2df\7\4\2\2ed\3\2\2\2ef\3\2\2\2fh\3\2\2\2gi\7\n\2\2hg\3\2"+
		"\2\2hi\3\2\2\2ij\3\2\2\2jk\7\13\2\2kl\7(\2\2ln\7\7\2\2mo\5\60\31\2nm\3"+
		"\2\2\2no\3\2\2\2op\3\2\2\2pq\7\b\2\2qr\5,\27\2r\13\3\2\2\2su\5\16\b\2"+
		"tv\5\20\t\2ut\3\2\2\2uv\3\2\2\2v\r\3\2\2\2wx\7\f\2\2xy\7\7\2\2yz\5\66"+
		"\34\2z{\7\b\2\2{|\5,\27\2|\17\3\2\2\2}~\7\r\2\2~\177\5,\27\2\177\21\3"+
		"\2\2\2\u0080\u0081\7\16\2\2\u0081\u0082\7\7\2\2\u0082\u0083\5\66\34\2"+
		"\u0083\u0084\7\b\2\2\u0084\u0085\5,\27\2\u0085\23\3\2\2\2\u0086\u0087"+
		"\7\17\2\2\u0087\u0088\7\7\2\2\u0088\u0089\7(\2\2\u0089\u008a\7\20\2\2"+
		"\u008a\u008b\5\66\34\2\u008b\u008c\7\21\2\2\u008c\u008f\5\66\34\2\u008d"+
		"\u008e\7\22\2\2\u008e\u0090\5\66\34\2\u008f\u008d\3\2\2\2\u008f\u0090"+
		"\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0092\7\b\2\2\u0092\u0093\5,\27\2\u0093"+
		"\25\3\2\2\2\u0094\u0095\7\23\2\2\u0095\u0096\7\7\2\2\u0096\u0097\5\66"+
		"\34\2\u0097\u0098\7\b\2\2\u0098\u009a\7\24\2\2\u0099\u009b\5\30\r\2\u009a"+
		"\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2"+
		"\2\2\u009d\u009f\3\2\2\2\u009e\u00a0\5\32\16\2\u009f\u009e\3\2\2\2\u009f"+
		"\u00a0\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\7\25\2\2\u00a2\27\3\2\2"+
		"\2\u00a3\u00a4\7\26\2\2\u00a4\u00a5\5\66\34\2\u00a5\u00a6\7\27\2\2\u00a6"+
		"\u00a7\5,\27\2\u00a7\31\3\2\2\2\u00a8\u00a9\7\30\2\2\u00a9\u00aa\7\27"+
		"\2\2\u00aa\u00ab\5,\27\2\u00ab\33\3\2\2\2\u00ac\u00b1\5\36\20\2\u00ad"+
		"\u00b1\5 \21\2\u00ae\u00b1\5\"\22\2\u00af\u00b1\5$\23\2\u00b0\u00ac\3"+
		"\2\2\2\u00b0\u00ad\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0\u00af\3\2\2\2\u00b1"+
		"\35\3\2\2\2\u00b2\u00b4\7\4\2\2\u00b3\u00b2\3\2\2\2\u00b3\u00b4\3\2\2"+
		"\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6\7\31\2\2\u00b6\u00b9\7(\2\2\u00b7\u00b8"+
		"\7\32\2\2\u00b8\u00ba\5\66\34\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2"+
		"\2\u00ba\37\3\2\2\2\u00bb\u00bd\5\64\33\2\u00bc\u00be\5\62\32\2\u00bd"+
		"\u00bc\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00c2\3\2\2\2\u00bf\u00c3\7-"+
		"\2\2\u00c0\u00c1\t\2\2\2\u00c1\u00c3\5\66\34\2\u00c2\u00bf\3\2\2\2\u00c2"+
		"\u00c0\3\2\2\2\u00c3!\3\2\2\2\u00c4\u00c5\5\64\33\2\u00c5\u00c7\7\7\2"+
		"\2\u00c6\u00c8\5.\30\2\u00c7\u00c6\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9"+
		"\3\2\2\2\u00c9\u00ca\7\b\2\2\u00ca#\3\2\2\2\u00cb\u00cd\7\33\2\2\u00cc"+
		"\u00ce\5\66\34\2\u00cd\u00cc\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce%\3\2\2"+
		"\2\u00cf\u00d2\5(\25\2\u00d0\u00d2\5*\26\2\u00d1\u00cf\3\2\2\2\u00d1\u00d0"+
		"\3\2\2\2\u00d2\'\3\2\2\2\u00d3\u00d4\7\34\2\2\u00d4\u00d5\7\35\2\2\u00d5"+
		"\u00d6\7(\2\2\u00d6\u00d7\t\3\2\2\u00d7)\3\2\2\2\u00d8\u00d9\7\34\2\2"+
		"\u00d9\u00da\7\36\2\2\u00da\u00db\t\4\2\2\u00db+\3\2\2\2\u00dc\u00e0\7"+
		"\24\2\2\u00dd\u00df\5\4\3\2\u00de\u00dd\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0"+
		"\u00e1\3\2\2\2\u00e0\u00de\3\2\2\2\u00e1\u00e3\3\2\2\2\u00e2\u00e0\3\2"+
		"\2\2\u00e3\u00e6\7\25\2\2\u00e4\u00e6\5\4\3\2\u00e5\u00dc\3\2\2\2\u00e5"+
		"\u00e4\3\2\2\2\u00e6-\3\2\2\2\u00e7\u00ea\5\66\34\2\u00e8\u00e9\7\37\2"+
		"\2\u00e9\u00eb\5.\30\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb/"+
		"\3\2\2\2\u00ec\u00ef\7(\2\2\u00ed\u00ee\7\37\2\2\u00ee\u00f0\5\60\31\2"+
		"\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\61\3\2\2\2\u00f1\u00f2"+
		"\7 \2\2\u00f2\u00f3\5\66\34\2\u00f3\u00f5\7!\2\2\u00f4\u00f6\5\62\32\2"+
		"\u00f5\u00f4\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\63\3\2\2\2\u00f7\u00fa"+
		"\7(\2\2\u00f8\u00f9\7\"\2\2\u00f9\u00fb\5\64\33\2\u00fa\u00f8\3\2\2\2"+
		"\u00fa\u00fb\3\2\2\2\u00fb\65\3\2\2\2\u00fc\u00fd\b\34\1\2\u00fd\u00fe"+
		"\7\7\2\2\u00fe\u00ff\5\66\34\2\u00ff\u0100\7\b\2\2\u0100\u0103\3\2\2\2"+
		"\u0101\u0103\58\35\2\u0102\u00fc\3\2\2\2\u0102\u0101\3\2\2\2\u0103\u0109"+
		"\3\2\2\2\u0104\u0105\f\5\2\2\u0105\u0106\7.\2\2\u0106\u0108\5\66\34\6"+
		"\u0107\u0104\3\2\2\2\u0108\u010b\3\2\2\2\u0109\u0107\3\2\2\2\u0109\u010a"+
		"\3\2\2\2\u010a\67\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u010e\5\64\33\2\u010d"+
		"\u010f\5\62\32\2\u010e\u010d\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u011e\3"+
		"\2\2\2\u0110\u0112\5\"\22\2\u0111\u0113\5\62\32\2\u0112\u0111\3\2\2\2"+
		"\u0112\u0113\3\2\2\2\u0113\u011e\3\2\2\2\u0114\u011e\5:\36\2\u0115\u011e"+
		"\7&\2\2\u0116\u011e\7)\2\2\u0117\u011e\7*\2\2\u0118\u011e\7+\2\2\u0119"+
		"\u011a\7\24\2\2\u011a\u011b\5.\30\2\u011b\u011c\7\25\2\2\u011c\u011e\3"+
		"\2\2\2\u011d\u010c\3\2\2\2\u011d\u0110\3\2\2\2\u011d\u0114\3\2\2\2\u011d"+
		"\u0115\3\2\2\2\u011d\u0116\3\2\2\2\u011d\u0117\3\2\2\2\u011d\u0118\3\2"+
		"\2\2\u011d\u0119\3\2\2\2\u011e9\3\2\2\2\u011f\u0120\7#\2\2\u0120\u0121"+
		"\5\66\34\2\u0121\u0122\7$\2\2\u0122\u0123\5\66\34\2\u0123\u0124\7%\2\2"+
		"\u0124\u0125\5\66\34\2\u0125;\3\2\2\2\u0126\u0129\7(\2\2\u0127\u0128\7"+
		"\37\2\2\u0128\u012a\5<\37\2\u0129\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a"+
		"=\3\2\2\2#AHPS\\behnu\u008f\u009c\u009f\u00b0\u00b3\u00b9\u00bd\u00c2"+
		"\u00c7\u00cd\u00d1\u00e0\u00e5\u00ea\u00ef\u00f5\u00fa\u0102\u0109\u010e"+
		"\u0112\u011d\u0129";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}