package org.lucylang.ljvm.parser;

import org.lucylang.ljvm.exception.CompileException;

public class ParserException extends CompileException {
    public ParserException(String message) {
        super(message);
    }
}
