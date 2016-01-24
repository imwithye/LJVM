package org.lucylang.ljvm.value;

import org.lucylang.ljvm.exception.RuntimeException;

public class ValueUnavailableException extends RuntimeException {
    public ValueUnavailableException(Value value) {
        super("Value " + value + " unavailable");
    }
}
