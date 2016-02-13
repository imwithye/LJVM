package org.lucylang.ljvm.machine.instruction;

import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.scope.OverdefinedException;
import org.lucylang.ljvm.machine.Register;
import org.lucylang.ljvm.scope.UndefinedException;
import org.lucylang.ljvm.type.TypeUnmatchedException;
import org.lucylang.ljvm.value.NumberValue;
import org.lucylang.ljvm.value.StringValue;
import org.lucylang.ljvm.value.Value;
import org.lucylang.ljvm.value.ValueUnavailableException;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Instruction implements Serializable {
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
            if (!(this.getOperand(i).getValue() instanceof String)) {
                errorRefs.add(i);
            } else {
                String ref = (String) this.getOperand(i).getValue();
                if (ref == null) {
                    errorRefs.add(i);
                }
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

    public String toString() {
        try {
            String string = new String();
            string += this.type;
            for (int i = 0; i < this.size(); i++) {
                Operand op = this.getOperand(i);
                if (op.getValue() instanceof String) {
                    string += " " + op.getValue();
                } else if (op.getValue() instanceof StringValue) {
                    String value = ((ValueOperand) op).getValue().stringValue();
                    if (value.equals("\t")) {
                        value = "\\t";
                    } else if (value.equals("\n")) {
                        value = "\\n";
                    }
                    string += " " + "\"" + value + "\"";
                } else {
                    string += " " + ((ValueOperand) op).getValue().stringValue();
                }
            }
            return string;
        } catch (Exception e) {
            return new String();
        }
    }

    public boolean execute(Machine vm, Module module) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        this.validate();
        return this.executeValid(vm, module);
    }

    public abstract boolean executeValid(Machine vm, Module module) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException;
}
