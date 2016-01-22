package org.lucylang.ljvm.machine.instruction;

import java.io.Serializable;

public abstract class Operand<T> implements Serializable {
    public abstract T getValue();
}
