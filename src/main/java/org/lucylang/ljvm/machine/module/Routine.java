package org.lucylang.ljvm.machine.module;

import org.lucylang.ljvm.machine.instruction.CallInstruction;
import org.lucylang.ljvm.machine.instruction.Instruction;
import org.lucylang.ljvm.machine.instruction.InvalidInstruction;
import org.lucylang.ljvm.machine.instruction.RefOperand;

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

    public Routine adjustCalls(Module module) throws InvalidInstruction {
        String moduleName = module.getName();
        for (int i = 0; i < instructions.size(); i++) {
            Instruction ins = instructions.get(i);
            if (ins instanceof CallInstruction) {
                CallInstruction call = (CallInstruction) ins;
                if(!call.getRef(0).contains("::") && !call.getRef(0).equals("main")) {
                    instructions.remove(i);
                    instructions.add(i, new CallInstruction(new RefOperand(moduleName + "::" + call.getRef(0))));
                }
            }
        }
        return this;
    }

    public Instruction[] getInstructions() {
        return this.instructions.toArray(new Instruction[0]);
    }

    public ArrayList<Instruction> getInstructionsList() {
        return this.instructions;
    }
}
