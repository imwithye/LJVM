package org.lucylang.ljvm.scope;

import org.lucylang.ljvm.exception.RuntimeException;

public class OverdefinedException extends RuntimeException {
    public OverdefinedException(Object key, Scope scope) {
        super(key + " has already defined in scope " + scope);
    }

    public OverdefinedException(String message) {
        super(message);
    }

}
