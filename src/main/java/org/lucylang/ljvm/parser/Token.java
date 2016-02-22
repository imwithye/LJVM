package org.lucylang.ljvm.parser;

public class Token {
    public enum Type {
        VAR, FUNC, RETURN, IF, ELSE, NONE, TRUE, FALSE, WHILE,
        LCURLY, RCURLY, LPAREN, RPAREN, SEMICOLON,
        ASSIGN, PLUS, MINUS, TIMES, DIV, GT, LT, GEQ, LEQ, EQUAL, NEQ, NOT,
        NEWLINE, NUMBER_LITERAL, ID, STRING_LITERAL,
        EOF,
    }

    private final Type type;
    private final int line, column;
    private final String lexeme;

    public Token(Type type, int line, int column, String lexeme) {
        assert type != null;
        assert line >= 0;
        assert column >= 0;
        assert lexeme != null;

        this.type = type;
        this.line = line;
        this.column = column;
        this.lexeme = lexeme;
    }

    public Type getType() {
        return this.type;
    }

    public String getLexeme() {
        return this.lexeme;
    }

    public boolean isEOF() {
        return type == Type.EOF;
    }

    @Override
    public String toString() {
        return type + "@" + line + ":" + column + "='" + lexeme + "'";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + column;
        result = prime * result + lexeme.hashCode();
        result = prime * result + line;
        return prime * result + type.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Token) {
            Token that = (Token) obj;
            return this.type == that.type &&
                    this.line == that.line &&
                    this.column == that.column &&
                    this.lexeme.equals(that.lexeme);
        } else {
            return false;
        }
    }
}
