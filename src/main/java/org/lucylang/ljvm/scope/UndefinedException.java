package org.lucylang.ljvm.scope;

import org.lucylang.ljvm.exception.RuntimeException;

public class UndefinedException extends RuntimeException {
    public UndefinedException(Object key, Scope scope) {
        super(key + " is not defined in scope " + scope);
    }
}
