package org.lucylang.ljvm.machine.instruction;

import org.lucylang.ljvm.exception.RuntimeException;

public class InvalidInstruction extends RuntimeException {
    public InvalidInstruction() {
        super(null);
    }
}
