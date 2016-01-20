package org.lucylang.ljvm.machine.instruction;

public class RefOperand extends Operand<String> {
    private final String ref;

    public RefOperand(String ref) {
        this.ref = ref;
    }

    @Override
    public String getValue() {
        return this.ref;
    }
}
