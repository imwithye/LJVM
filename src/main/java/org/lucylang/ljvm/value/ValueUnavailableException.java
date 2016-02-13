package org.lucylang.ljvm.value;

import org.lucylang.ljvm.exception.RuntimeException;

public class ValueUnavailableException extends RuntimeException {
    public ValueUnavailableException(String message) {
        super(message);
    }

    public ValueUnavailableException(Value expectValue, Value realValue) {
        super(expectValue + " value is unavailable for " + realValue);
    }

    public ValueUnavailableException(String expectValue, Value realValue) {
        super(expectValue + " value is unavailable for " + realValue);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ValueUnavailableException) {
            return ((ValueUnavailableException) other).getMessage().equals(this.getMessage());
        }
        return false;
    }
}
