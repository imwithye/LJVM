package org.lucylang.ljvm.machine.module;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.lucylang.ljvm.machine.instruction.Instruction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Routine implements Serializable {
    private ArrayList<Instruction> instructions;

    public Routine(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
    }

    public Routine(Instruction[] instructions) {
        this.instructions = new ArrayList<Instruction>(Arrays.asList(instructions));
    }

    public Routine() {
        this.instructions = new ArrayList<Instruction>();
    }

    public static Routine loadFromJSON(JSONObject object) {
        try {
            JSONArray instructions = object.getJSONArray("instructions");
            String opcode;
            for (int i = 0; i < instructions.length(); i++) {
                JSONObject instruction = instructions.getJSONObject(i);
                opcode = instruction.getString("opcode");
            }
            Routine routine = new Routine();
            return routine;
        } catch (JSONException e) {
            return null;
        }
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
