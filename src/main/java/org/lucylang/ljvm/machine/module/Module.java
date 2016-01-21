package org.lucylang.ljvm.machine.module;

import org.lucylang.ljvm.machine.instruction.DefInstruction;
import org.lucylang.ljvm.scope.OverdefinedException;
import org.lucylang.ljvm.scope.UndefinedException;

import java.util.ArrayList;
import java.util.HashMap;

public class Module {
    private HashMap<String, Routine> functions;
    private ArrayList<DefInstruction> vars;

    public Module() {
        this.functions = new HashMap<String, Routine>();
        this.vars = new ArrayList<DefInstruction>();
    }

    public boolean hasMain() {
        return functions.get("main") != null;
    }

    public Module defineRoutine(String name, Routine routine) throws OverdefinedException {
        if (this.functions.get(name) != null) {
            throw new OverdefinedException();
        }
        this.functions.put(name, routine);
        return this;
    }

    public Routine getRoutine(String name) throws UndefinedException {
        Routine routine = this.functions.get(name);
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
}
