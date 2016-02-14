package org.lucylang.ljvm.machine.module;

import org.lucylang.ljvm.machine.instruction.Instruction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Routine implements Serializable {
    protected ArrayList<Instruction> instructions;

    public Routine(ArrayList<Instruction> instructions) {
        if (instructions == null) {
            this.instructions = new ArrayList<Instruction>();
        } else {
            this.instructions = instructions;
        }
    }

    public Routine(Instruction[] instructions) {
        if (instructions == null) {
            this.instructions = new ArrayList<Instruction>();
        } else {
            this.instructions = new ArrayList<Instruction>(Arrays.asList(instructions));
        }
    }

    public Routine() {
        this.instructions = new ArrayList<Instruction>();
    }

    public Routine addInstruction(Instruction instruction) {
        this.instructions.add(instruction);
        return this;
    }

    public Instruction[] getInstructions() {
        return this.instructions.toArray(new Instruction[0]);
    }

    public ArrayList<Instruction> getInstructionsList() {
        return this.instructions;
    }
}
