package org.lucylang.ljvm.exception;

public class CompileException extends Exception {
    public CompileException(String message) {
        super("Compile Error: " + message);
    }
}
