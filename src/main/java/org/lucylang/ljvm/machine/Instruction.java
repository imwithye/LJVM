package org.lucylang.ljvm.machine;

import org.lucylang.ljvm.value.Value;

public class Instruction {
    private InstructionType type;
    private boolean[] isRef;
    private String[] ids;
    private Value[] values;

    public static Instruction create(InstructionType type) {
        Instruction i = new Instruction();
        i.type = type;
        i.isRef = new boolean[3];
        i.ids = new String[3];
        i.values = new Value[3];
        return i;
    }

    public Instruction first(String id) {
        this.isRef[0] = true;
        this.ids[0] = id;
        return this;
    }

    public Instruction first(Value value) {
        this.isRef[0] = false;
        this.values[0] = value;
        return this;
    }

    public Instruction second(String id) {
        this.isRef[1] = true;
        this.ids[1] = id;
        return this;
    }

    public Instruction second(Value value) {
        this.isRef[1] = false;
        this.values[1] = value;
        return this;
    }

    public Instruction third(String id) {
        this.isRef[1] = true;
        this.ids[1] = id;
        return this;
    }

    public Instruction third(Value value) {
        this.isRef[2] = false;
        this.values[2] = value;
        return this;
    }

    public boolean isRef(int pos) {
        return this.isRef[pos];
    }

    public String getRef(int pos) {
        return this.ids[pos];
    }

    public Value getValue(int pos) {
        return this.values[pos];
    }

    public InstructionType getType() {
        return this.type;
    }
}
