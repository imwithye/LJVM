package org.lucylang.ljvm.parser;

import static org.lucylang.ljvm.parser.Token.Type.*;

%%

%public
%class Lexer
%function nextToken
%type Token
%unicode
%line
%column

%{
    private Token token(Token.Type type) {
        return new Token(type, yyline, yycolumn, yytext());
    }

    private Token token(Token.Type type, String text) {
        return new Token(type, yyline, yycolumn, text);
    }

    private Token token(Token.Type type, String text, int line, int column) {
        return new Token(type, line, column, text);
    }
%}

%state STRING

%%

<YYINITIAL> {
    "var"       { return token(VAR); }
    "func"      { return token(FUNC); }
    "return"    { return token(RETURN); }
    "true"      { return token(TRUE); }
    "false"     { return token(FALSE); }
    "none"      { return token(NONE); }
    "if"        { return token(IF); }
    "else"      { return token(ELSE); }
    "while"     { return token(WHILE); }
}