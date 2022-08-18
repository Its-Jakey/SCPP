grammar SCPP;

program
: statement+;

statement
: bracketStatement
| nonBracketStatement ';'
| directive;


bracketStatement
: namespaceDeclaration
| functionDeclaration
| ifStatement
| whileLoop
| forLoop
| switchStatement;

namespaceDeclaration
: pub='public'? 'namespace' ID (('uses' '(' idList ')')? codeBlock | 'is' ID ';');

functionDeclaration
: pub='public'? inline='inline'? 'func' ID '(' functionArgumentArray? ')' codeBlock;

ifStatement
: ifPart elsePart?;

ifPart
: 'if' '(' expression ')' codeBlock;

elsePart
: 'else' codeBlock;

whileLoop
: 'while' '(' expression ')' codeBlock;

forLoop
: 'for' '(' ID 'from' expression 'to' expression ('by' expression)? ')' codeBlock;

switchStatement
: 'switch' '(' expression ')' '{' caseStatement+ defaultStatement? '}';

caseStatement
: 'case' expression '->' codeBlock;

defaultStatement
: 'default' '->' codeBlock;


nonBracketStatement
: variableDeclaration
| variableValueChange
| functionCall
| returnStatement;

variableDeclaration
: pub='public'? 'var' ID ('=' expression)?;

variableValueChange
: variable arrayIndex? (VARIABLE_SINGLE_MODIFIER | (VARIABLE_MODIFIER | '=') expression);

functionCall
: variable '(' argumentArray? ')';

returnStatement
: 'return' expression?;


directive
: defineDirective
| includeDirective;

defineDirective
: '#' 'define' ID (INT | HEX | BIN);

includeDirective
: '#' 'include' (STRING | LIBRARY);



codeBlock
: '{' statement*? '}'
| statement;

argumentArray
: expression (',' argumentArray)?;

functionArgumentArray
: ID (',' functionArgumentArray)?;

arrayIndex
: '[' expression ']' arrayIndex?;

variable
: ID ('::' variable)?;

expression
: expression OPERATOR expression
| '(' expression ')'
| value;

value
: (variable arrayIndex?)
| (functionCall arrayIndex?)
| conditionalValue
| STRING
| INT
| HEX
| BIN
| '{' argumentArray '}';

conditionalValue
: '?' expression ':' expression '!' expression;

idList
: ID (',' idList)?;



STRING: '"' (~'"'|'\\"')* '"';
LIBRARY: '<' ([a-z] | [A-Z] | '_')+ ([a-z] | [A-Z] | '_' | [0-9])* '>';
ID: ([a-z] | [A-Z] | '_')+ ([a-z] | [A-Z] | '_' | [0-9])*;
INT: ('-'? [0-9]+ ('.' [0-9]+)?) | '.' [0-9]+;
HEX: '0x' ([0-9] | [a-f] | [A-F])+;
BIN: '0b' ('0' | '1')+;
VARIABLE_MODIFIER: OPERATOR '=';
VARIABLE_SINGLE_MODIFIER: '++' | '--';
OPERATOR: '+' | '-' | '*' | '/' | '>>' | '<<' | '|' | '&' | '^' | '%' | '||' | '&&' | '>' | '<' | '==' | '!=' | '>=' | '<=' | '..';
WS: [ \t\r\n]+ -> skip;
SINGLE_COMMENT: '//' .*? '\n' -> skip;
BLOCK_COMMENT: '/*' .*? '*/' -> skip;
