package org.lucylang.ljvm.scope;

import org.lucylang.ljvm.exception.RuntimeException;

public class OverdefinedException extends RuntimeException {
    public OverdefinedException() {
        super(null);
    }
}
