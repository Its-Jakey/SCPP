package antlr;// Generated from SCPP.g4 by ANTLR 4.7.1

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SCPPLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, STRING=27, LIBRARY=28, ID=29, INT=30, HEX=31, BIN=32, 
		VARIABLE_MODIFIER=33, VARIABLE_SINGLE_MODIFIER=34, OPERATOR=35, WS=36, 
		SINGLE_COMMENT=37, BLOCK_COMMENT=38;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
		"T__25", "STRING", "LIBRARY", "ID", "INT", "HEX", "BIN", "VARIABLE_MODIFIER", 
		"VARIABLE_SINGLE_MODIFIER", "OPERATOR", "WS", "SINGLE_COMMENT", "BLOCK_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "'public'", "'namespace'", "'is'", "'inline'", "'func'", 
		"'('", "')'", "'if'", "'while'", "'for'", "'from'", "'to'", "'by'", "'var'", 
		"'='", "'return'", "'#'", "'define'", "'include'", "'{'", "'}'", "','", 
		"'['", "']'", "'::'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "STRING", "LIBRARY", "ID", "INT", "HEX", "BIN", "VARIABLE_MODIFIER", 
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


	public SCPPLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SCPP.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2(\u0144\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\3\2\3\2\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31"+
		"\3\31\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\34\7\34\u00bd\n\34\f\34"+
		"\16\34\u00c0\13\34\3\34\3\34\3\35\3\35\6\35\u00c6\n\35\r\35\16\35\u00c7"+
		"\3\35\7\35\u00cb\n\35\f\35\16\35\u00ce\13\35\3\35\3\35\3\36\6\36\u00d3"+
		"\n\36\r\36\16\36\u00d4\3\36\7\36\u00d8\n\36\f\36\16\36\u00db\13\36\3\37"+
		"\5\37\u00de\n\37\3\37\6\37\u00e1\n\37\r\37\16\37\u00e2\3\37\3\37\6\37"+
		"\u00e7\n\37\r\37\16\37\u00e8\5\37\u00eb\n\37\3\37\3\37\6\37\u00ef\n\37"+
		"\r\37\16\37\u00f0\5\37\u00f3\n\37\3 \3 \3 \3 \6 \u00f9\n \r \16 \u00fa"+
		"\3!\3!\3!\3!\6!\u0101\n!\r!\16!\u0102\3\"\3\"\3\"\3#\3#\3#\3#\5#\u010c"+
		"\n#\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\5$\u0121"+
		"\n$\3%\6%\u0124\n%\r%\16%\u0125\3%\3%\3&\3&\3&\3&\7&\u012e\n&\f&\16&\u0131"+
		"\13&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\7\'\u013b\n\'\f\'\16\'\u013e\13\'\3\'"+
		"\3\'\3\'\3\'\3\'\4\u012f\u013c\2(\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(\3\2\13\3\2$$\5\2"+
		"C\\aac|\6\2\62;C\\aac|\3\2\62;\5\2\62;CHch\5\2,-//\61\61\5\2\'(``~~\4"+
		"\2>>@@\5\2\13\f\17\17\"\"\2\u015f\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2"+
		"\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2"+
		"\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\3"+
		"O\3\2\2\2\5Q\3\2\2\2\7X\3\2\2\2\tb\3\2\2\2\13e\3\2\2\2\rl\3\2\2\2\17q"+
		"\3\2\2\2\21s\3\2\2\2\23u\3\2\2\2\25x\3\2\2\2\27~\3\2\2\2\31\u0082\3\2"+
		"\2\2\33\u0087\3\2\2\2\35\u008a\3\2\2\2\37\u008d\3\2\2\2!\u0091\3\2\2\2"+
		"#\u0093\3\2\2\2%\u009a\3\2\2\2\'\u009c\3\2\2\2)\u00a3\3\2\2\2+\u00ab\3"+
		"\2\2\2-\u00ad\3\2\2\2/\u00af\3\2\2\2\61\u00b1\3\2\2\2\63\u00b3\3\2\2\2"+
		"\65\u00b5\3\2\2\2\67\u00b8\3\2\2\29\u00c3\3\2\2\2;\u00d2\3\2\2\2=\u00f2"+
		"\3\2\2\2?\u00f4\3\2\2\2A\u00fc\3\2\2\2C\u0104\3\2\2\2E\u010b\3\2\2\2G"+
		"\u0120\3\2\2\2I\u0123\3\2\2\2K\u0129\3\2\2\2M\u0136\3\2\2\2OP\7=\2\2P"+
		"\4\3\2\2\2QR\7r\2\2RS\7w\2\2ST\7d\2\2TU\7n\2\2UV\7k\2\2VW\7e\2\2W\6\3"+
		"\2\2\2XY\7p\2\2YZ\7c\2\2Z[\7o\2\2[\\\7g\2\2\\]\7u\2\2]^\7r\2\2^_\7c\2"+
		"\2_`\7e\2\2`a\7g\2\2a\b\3\2\2\2bc\7k\2\2cd\7u\2\2d\n\3\2\2\2ef\7k\2\2"+
		"fg\7p\2\2gh\7n\2\2hi\7k\2\2ij\7p\2\2jk\7g\2\2k\f\3\2\2\2lm\7h\2\2mn\7"+
		"w\2\2no\7p\2\2op\7e\2\2p\16\3\2\2\2qr\7*\2\2r\20\3\2\2\2st\7+\2\2t\22"+
		"\3\2\2\2uv\7k\2\2vw\7h\2\2w\24\3\2\2\2xy\7y\2\2yz\7j\2\2z{\7k\2\2{|\7"+
		"n\2\2|}\7g\2\2}\26\3\2\2\2~\177\7h\2\2\177\u0080\7q\2\2\u0080\u0081\7"+
		"t\2\2\u0081\30\3\2\2\2\u0082\u0083\7h\2\2\u0083\u0084\7t\2\2\u0084\u0085"+
		"\7q\2\2\u0085\u0086\7o\2\2\u0086\32\3\2\2\2\u0087\u0088\7v\2\2\u0088\u0089"+
		"\7q\2\2\u0089\34\3\2\2\2\u008a\u008b\7d\2\2\u008b\u008c\7{\2\2\u008c\36"+
		"\3\2\2\2\u008d\u008e\7x\2\2\u008e\u008f\7c\2\2\u008f\u0090\7t\2\2\u0090"+
		" \3\2\2\2\u0091\u0092\7?\2\2\u0092\"\3\2\2\2\u0093\u0094\7t\2\2\u0094"+
		"\u0095\7g\2\2\u0095\u0096\7v\2\2\u0096\u0097\7w\2\2\u0097\u0098\7t\2\2"+
		"\u0098\u0099\7p\2\2\u0099$\3\2\2\2\u009a\u009b\7%\2\2\u009b&\3\2\2\2\u009c"+
		"\u009d\7f\2\2\u009d\u009e\7g\2\2\u009e\u009f\7h\2\2\u009f\u00a0\7k\2\2"+
		"\u00a0\u00a1\7p\2\2\u00a1\u00a2\7g\2\2\u00a2(\3\2\2\2\u00a3\u00a4\7k\2"+
		"\2\u00a4\u00a5\7p\2\2\u00a5\u00a6\7e\2\2\u00a6\u00a7\7n\2\2\u00a7\u00a8"+
		"\7w\2\2\u00a8\u00a9\7f\2\2\u00a9\u00aa\7g\2\2\u00aa*\3\2\2\2\u00ab\u00ac"+
		"\7}\2\2\u00ac,\3\2\2\2\u00ad\u00ae\7\177\2\2\u00ae.\3\2\2\2\u00af\u00b0"+
		"\7.\2\2\u00b0\60\3\2\2\2\u00b1\u00b2\7]\2\2\u00b2\62\3\2\2\2\u00b3\u00b4"+
		"\7_\2\2\u00b4\64\3\2\2\2\u00b5\u00b6\7<\2\2\u00b6\u00b7\7<\2\2\u00b7\66"+
		"\3\2\2\2\u00b8\u00be\7$\2\2\u00b9\u00bd\n\2\2\2\u00ba\u00bb\7^\2\2\u00bb"+
		"\u00bd\7$\2\2\u00bc\u00b9\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00c0\3\2"+
		"\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c1\3\2\2\2\u00c0"+
		"\u00be\3\2\2\2\u00c1\u00c2\7$\2\2\u00c28\3\2\2\2\u00c3\u00c5\7>\2\2\u00c4"+
		"\u00c6\t\3\2\2\u00c5\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c5\3\2"+
		"\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00cc\3\2\2\2\u00c9\u00cb\t\4\2\2\u00ca"+
		"\u00c9\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2"+
		"\2\2\u00cd\u00cf\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d0\7@\2\2\u00d0"+
		":\3\2\2\2\u00d1\u00d3\t\3\2\2\u00d2\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2"+
		"\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d9\3\2\2\2\u00d6\u00d8"+
		"\t\4\2\2\u00d7\u00d6\3\2\2\2\u00d8\u00db\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9"+
		"\u00da\3\2\2\2\u00da<\3\2\2\2\u00db\u00d9\3\2\2\2\u00dc\u00de\7/\2\2\u00dd"+
		"\u00dc\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00e0\3\2\2\2\u00df\u00e1\t\5"+
		"\2\2\u00e0\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2"+
		"\u00e3\3\2\2\2\u00e3\u00ea\3\2\2\2\u00e4\u00e6\7\60\2\2\u00e5\u00e7\t"+
		"\5\2\2\u00e6\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e8"+
		"\u00e9\3\2\2\2\u00e9\u00eb\3\2\2\2\u00ea\u00e4\3\2\2\2\u00ea\u00eb\3\2"+
		"\2\2\u00eb\u00f3\3\2\2\2\u00ec\u00ee\7\60\2\2\u00ed\u00ef\t\5\2\2\u00ee"+
		"\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2"+
		"\2\2\u00f1\u00f3\3\2\2\2\u00f2\u00dd\3\2\2\2\u00f2\u00ec\3\2\2\2\u00f3"+
		">\3\2\2\2\u00f4\u00f5\7\62\2\2\u00f5\u00f6\7z\2\2\u00f6\u00f8\3\2\2\2"+
		"\u00f7\u00f9\t\6\2\2\u00f8\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00f8"+
		"\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb@\3\2\2\2\u00fc\u00fd\7\62\2\2\u00fd"+
		"\u00fe\7d\2\2\u00fe\u0100\3\2\2\2\u00ff\u0101\4\62\63\2\u0100\u00ff\3"+
		"\2\2\2\u0101\u0102\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103"+
		"B\3\2\2\2\u0104\u0105\5G$\2\u0105\u0106\7?\2\2\u0106D\3\2\2\2\u0107\u0108"+
		"\7-\2\2\u0108\u010c\7-\2\2\u0109\u010a\7/\2\2\u010a\u010c\7/\2\2\u010b"+
		"\u0107\3\2\2\2\u010b\u0109\3\2\2\2\u010cF\3\2\2\2\u010d\u0121\t\7\2\2"+
		"\u010e\u010f\7@\2\2\u010f\u0121\7@\2\2\u0110\u0111\7>\2\2\u0111\u0121"+
		"\7>\2\2\u0112\u0121\t\b\2\2\u0113\u0114\7~\2\2\u0114\u0121\7~\2\2\u0115"+
		"\u0116\7(\2\2\u0116\u0121\7(\2\2\u0117\u0121\t\t\2\2\u0118\u0119\7?\2"+
		"\2\u0119\u0121\7?\2\2\u011a\u011b\7#\2\2\u011b\u0121\7?\2\2\u011c\u011d"+
		"\7@\2\2\u011d\u0121\7?\2\2\u011e\u011f\7>\2\2\u011f\u0121\7?\2\2\u0120"+
		"\u010d\3\2\2\2\u0120\u010e\3\2\2\2\u0120\u0110\3\2\2\2\u0120\u0112\3\2"+
		"\2\2\u0120\u0113\3\2\2\2\u0120\u0115\3\2\2\2\u0120\u0117\3\2\2\2\u0120"+
		"\u0118\3\2\2\2\u0120\u011a\3\2\2\2\u0120\u011c\3\2\2\2\u0120\u011e\3\2"+
		"\2\2\u0121H\3\2\2\2\u0122\u0124\t\n\2\2\u0123\u0122\3\2\2\2\u0124\u0125"+
		"\3\2\2\2\u0125\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0127\3\2\2\2\u0127"+
		"\u0128\b%\2\2\u0128J\3\2\2\2\u0129\u012a\7\61\2\2\u012a\u012b\7\61\2\2"+
		"\u012b\u012f\3\2\2\2\u012c\u012e\13\2\2\2\u012d\u012c\3\2\2\2\u012e\u0131"+
		"\3\2\2\2\u012f\u0130\3\2\2\2\u012f\u012d\3\2\2\2\u0130\u0132\3\2\2\2\u0131"+
		"\u012f\3\2\2\2\u0132\u0133\7\f\2\2\u0133\u0134\3\2\2\2\u0134\u0135\b&"+
		"\2\2\u0135L\3\2\2\2\u0136\u0137\7\61\2\2\u0137\u0138\7,\2\2\u0138\u013c"+
		"\3\2\2\2\u0139\u013b\13\2\2\2\u013a\u0139\3\2\2\2\u013b\u013e\3\2\2\2"+
		"\u013c\u013d\3\2\2\2\u013c\u013a\3\2\2\2\u013d\u013f\3\2\2\2\u013e\u013c"+
		"\3\2\2\2\u013f\u0140\7,\2\2\u0140\u0141\7\61\2\2\u0141\u0142\3\2\2\2\u0142"+
		"\u0143\b\'\2\2\u0143N\3\2\2\2\33\2\u00bc\u00be\u00c5\u00c7\u00ca\u00cc"+
		"\u00d2\u00d4\u00d7\u00d9\u00dd\u00e2\u00e8\u00ea\u00f0\u00f2\u00f8\u00fa"+
		"\u0102\u010b\u0120\u0125\u012f\u013c\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}