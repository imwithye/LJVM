package org.lucylang.ljvm.machine.instruction;

import java.util.ArrayList;

public abstract class Instruction {
    protected Type type = Type.NULL;
    protected ArrayList<Operand> operands;
    protected int validSize;
    protected int[] validRefs;

    public Type getType() {
        return this.type;
    }

    public boolean isValid() {
        try {
            if (this.validate()) {
                return true;
            } else {
                return false;
            }
        } catch (InvalidInstruction e) {
            return false;
        }
    }

    public boolean validate() throws InvalidInstruction {
        return this.checkValid(this.validSize, this.validRefs);
    }

    public int size() {
        return this.operands.size();
    }

    public Operand getOperand(int pos) {
        return this.operands.get(pos);
    }

    protected boolean checkValid(int size, int[] refs) throws InvalidInstruction {
        if (this.size() != size) {
            throw new InvalidInstruction();
        }
        ArrayList<Integer> errorRefs = new ArrayList<Integer>();
        for (int i = 0; i < refs.length; i++) {
            try {
                Operand<String> operand = (Operand<String>) this.getOperand(i);
                if (!(operand != null && operand.getValue() instanceof String && operand.getValue() != null)) {
                    errorRefs.add(i);
                }
            } catch (Exception e) {
                errorRefs.add(i);
            }
        }
        if (errorRefs.size() > 0) {
            throw new InvalidInstruction();
        }
        return true;
    }
}
