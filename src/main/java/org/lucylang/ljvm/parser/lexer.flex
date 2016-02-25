package org.lucylang.ljvm.parser;

import org.lucylang.ljvm.value.*;
import org.lucylang.ljvm.parser.Parser.Terminals;

%%

%public
%class Lexer
%extends beaver.Scanner
%function nextToken
%type beaver.Symbol
%yylexthrow beaver.Scanner.Exception
%eofval{
    return new beaver.Symbol(Terminals.EOF, "end-of-file");
%eofval}
%unicode
%line
%column

%{
    StringBuffer stringBuffer = new StringBuffer();

    private beaver.Symbol token(short id) {
        return new beaver.Symbol(id, yyline + 1, yycolumn + 1, yylength(), yytext());
    }

    private beaver.Symbol token(short id, Object value) {
        return new beaver.Symbol(id, yyline + 1, yycolumn + 1, yylength(), value);
    }
%}

%state STRING

%%

<YYINITIAL> {
    [\r\n]+     { return token(Terminals.STMT_TAIL); }

    "import"    { return token(Terminals.IMPORT); }
    "var"       { return token(Terminals.VAR); }
    "func"      { return token(Terminals.FUNC); }
    "return"    { return token(Terminals.RETURN); }
    "if"        { return token(Terminals.IF); }
    [\r\n]*"else"      { return token(Terminals.ELSE); }
    "while"     { return token(Terminals.WHILE); }

    "{"         { return token(Terminals.LCURLY); }
    "}"         { return token(Terminals.RCURLY); }
    "("         { return token(Terminals.LPAREN); }
    ")"         { return token(Terminals.RPAREN); }
    ";"         { return token(Terminals.SEMICOLON); }
    ","         { return token(Terminals.COMMA); }

    "="         { return token(Terminals.ASSIGN); }
    "+"         { return token(Terminals.PLUS); }
    "-"         { return token(Terminals.MINUS); }
    "*"         { return token(Terminals.TIMES); }
    "/"         { return token(Terminals.DIV); }
    ">"         { return token(Terminals.GT); }
    "<"         { return token(Terminals.LT); }
    ">="        { return token(Terminals.GEQ); }
    "<="        { return token(Terminals.LEQ); }
    "=="        { return token(Terminals.EQUAL); }
    "!="        { return token(Terminals.NEQ); }
    "!"         { return token(Terminals.NOT); }
    "not"       { return token(Terminals.NOT); }
    "and"       { return token(Terminals.AND); }
    "&&"        { return token(Terminals.AND); }
    "or"        { return token(Terminals.OR); }
    "||"        { return token(Terminals.OR); }
    "::"        { return token(Terminals.DOUBLE_COLON); }

    [\s\t]      { /* return */ }
    ;+          { return token(Terminals.SEMICOLON); }
    "none"      { return token(Terminals.NONE_LITERAL); }
    "true"      { return token(Terminals.BOOL_LITERAL, yytext()); }
    "false"     { return token(Terminals.BOOL_LITERAL, yytext()); }
    [0-9]*\.?[0-9]+         { return token(Terminals.NUMBER_LITERAL, yytext()); }
    [a-zA-Z][a-zA-Z0-9_]*   { return token(Terminals.ID, yytext()); }

    "\""        { stringBuffer.setLength(0); yybegin(STRING); }
}

<STRING> {
    "\""            { yybegin(YYINITIAL); return token(Terminals.STRING_LITERAL, stringBuffer.toString()); }
    [^\n\r\"\\]+    { stringBuffer.append(yytext()); }
    "\\t"           { stringBuffer.append('\t'); }
    "\\b"           { stringBuffer.append('\b'); }
    "\\n"           { stringBuffer.append('\n'); }
    "\\r"           { stringBuffer.append('\r'); }
    "\\f"           { stringBuffer.append('\f'); }
    "\\\""          { stringBuffer.append('\"'); }
    "\\\'"          { stringBuffer.append('\''); }
    "\\\\"          { stringBuffer.append('\\'); }
    \\.             { throw new beaver.Scanner.Exception("Illegal escape sequence \""+yytext()+"\""); }
    \r|\n|\r\n      { throw new beaver.Scanner.Exception("Unterminated string at end of line"); }
}

.               { throw new beaver.Scanner.Exception("unexpected character '" + yytext() + "'"); }