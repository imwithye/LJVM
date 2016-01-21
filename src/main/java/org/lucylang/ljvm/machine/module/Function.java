package org.lucylang.ljvm.machine.module;

import org.lucylang.ljvm.machine.instruction.Instruction;

import java.util.ArrayList;
import java.util.Arrays;

public class Function {
    private ArrayList<Instruction> instructions;

    public Function(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
    }

    public Function(Instruction[] instructions) {
        this.instructions = new ArrayList<Instruction>(Arrays.asList(instructions));
    }

    public Function() {
        this.instructions = new ArrayList<Instruction>();
    }

    public Function addInstruction(Instruction instruction) {
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
