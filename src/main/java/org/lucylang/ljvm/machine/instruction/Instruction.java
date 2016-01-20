package org.lucylang.ljvm.machine.instruction;

import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.machine.OverdefinedException;
import org.lucylang.ljvm.machine.Register;
import org.lucylang.ljvm.machine.UndefinedException;
import org.lucylang.ljvm.type.TypeUnmatchedException;
import org.lucylang.ljvm.value.Value;
import org.lucylang.ljvm.value.ValueUnavailableException;

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

    public String getRef(int pos) throws InvalidInstruction {
        Operand operand = this.getOperand(pos);
        if (operand.getValue() instanceof String) {
            return (String) operand.getValue();
        } else {
            throw new InvalidInstruction();
        }
    }

    public Value getValue(Machine vm, int pos) throws InvalidInstruction, UndefinedException {
        Operand operand = this.getOperand(pos);
        if (operand.getValue() instanceof String) {
            return vm.getValue((String) operand.getValue());
        } else if (operand.getValue() instanceof Value) {
            return (Value) operand.getValue();
        } else {
            throw new InvalidInstruction();
        }
    }

    public Register getRegister(Machine vm, int pos) throws InvalidInstruction, UndefinedException {
        Operand operand = this.getOperand(pos);
        if (operand.getValue() instanceof String) {
            return vm.getRegister((String) operand.getValue());
        } else {
            throw new InvalidInstruction();
        }
    }

    public void execute(Machine vm) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        this.validate();
        this.executeValid(vm);
    }

    public abstract void executeValid(Machine vm) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException;
}
