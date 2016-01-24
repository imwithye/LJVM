package org.lucylang.ljvm.parser;

import static org.lucylang.ljvm.parser.Token.Type.*;

%%

%public
%final
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
%}

NULL            = null
DEF             = def
MOV             = mov
ADD             = add
SUB             = sub
MUL             = mul
DIV             = div
AND             = and
OR              = or
NOT             = nor
EQU             = equ
LES             = les
GRE             = gre
LEQ             = leq
GEQ             = geq
PUSH            = push
POP             = pop
PEEK            = peek
CALL            = call
RET             = ret
GOTO            = goto
BEQ             = beq
BNE             = bne
NUM             = num
STR             = str
BOOL            = bool
PUT             = put
IDENT           = [a-zA-Z$]+[a-zA-Z0-9$_]*
LCURLY          = "{"
RCURLY          = "}"
SEMICOLON       = ";"
WhiteSpace      = [ ] | \t | \f | \n | \r

/* comments */
Comment = {TraditionalComment} | {DocumentationComment}
TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

%%

{NULL}          { return token(NULL); }
{DEF}           { return token(DEF); }
{MOV}           { return token(MOV); }
{ADD}           { return token(ADD); }
{SUB}           { return token(SUB); }
{MUL}           { return token(MUL); }
{DIV}           { return token(DIV); }
{AND}           { return token(AND); }
{OR}            { return token(OR); }
{NOT}           { return token(NOT); }
{EQU}           { return token(EQU); }
{LES}           { return token(LES); }
{GRE}           { return token(GRE); }
{LEQ}           { return token(LEQ); }
{GEQ}           { return token(GEQ); }
{PUSH}          { return token(PUSH); }
{POP}           { return token(POP); }
{PEEK}          { return token(PEEK); }
{CALL}          { return token(CALL); }
{RET}           { return token(RET); }
{GOTO}          { return token(GOTO); }
{BEQ}           { return token(BEQ); }
{BNE}           { return token(BNE); }
{NUM}           { return token(NUM); }
{STR}           { return token(STR); }
{BOOL}          { return token(BOOL); }
{PUT}           {return token(PUT); }
{IDENT}         { return token(IDENT); }
{LCURLY}        { return token(LCURLY); }
{RCURLY}        { return token(RCURLY); }
{SEMICOLON}     { return token(SEMICOLON); }

{WhiteSpace}    {}
{Comment}       {}
.               { }
<<EOF>>         { return token(EOF); }