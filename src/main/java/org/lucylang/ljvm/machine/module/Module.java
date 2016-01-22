package org.lucylang.ljvm.machine.module;

import org.lucylang.ljvm.machine.instruction.DefInstruction;
import org.lucylang.ljvm.machine.instruction.Instruction;
import org.lucylang.ljvm.scope.OverdefinedException;
import org.lucylang.ljvm.scope.UndefinedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Module implements Serializable {
    private HashMap<String, Routine> routines;
    private ArrayList<DefInstruction> vars;

    public Module() {
        this.routines = new HashMap<String, Routine>();
        this.vars = new ArrayList<DefInstruction>();
    }

    public boolean hasMain() {
        return this.routines.get("main") != null;
    }

    public Module defineRoutine(String name, Routine routine) throws OverdefinedException {
        if (this.routines.get(name) != null) {
            throw new OverdefinedException();
        }
        this.routines.put(name, routine);
        return this;
    }

    public Routine getRoutine(String name) throws UndefinedException {
        Routine routine = this.routines.get(name);
        if (routine == null) {
            throw new UndefinedException();
        }
        return routine;
    }

    public Module addVar(DefInstruction instruction) {
        this.vars.add(instruction);
        return this;
    }

    public Module addVars(DefInstruction[] instructions) {
        for (DefInstruction instruction : instructions) {
            this.addVar(instruction);
        }
        return this;
    }

    public DefInstruction[] getVars() {
        return this.vars.toArray(new DefInstruction[0]);
    }

    @Override
    public String toString() {
        try {
            String string = new String();
            for (DefInstruction i : getVars()) {
                string += i + "\n";
            }
            for (String key : this.routines.keySet()) {
                Routine routine = this.routines.get(key);
                String instruction = new String();
                for (Instruction i : routine.getInstructions()) {
                    instruction += "\t" + i + "\n";
                }
                string += "\n" + key + " {\n" + instruction + "}\n";
            }
            return string;
        } catch (Exception e) {
            return new String();
        }
    }
}
