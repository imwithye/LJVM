package org.lucylang.ljvm.machine.module;

import org.lucylang.ljvm.machine.instruction.DefInstruction;
import org.lucylang.ljvm.scope.OverdefinedException;
import org.lucylang.ljvm.scope.UndefinedException;

import java.util.ArrayList;
import java.util.HashMap;

public class Module {
    private HashMap<String, Function> functions;
    private ArrayList<DefInstruction> globalVariables;

    public Module() {
        this.functions = new HashMap<String, Function>();
        this.globalVariables = new ArrayList<DefInstruction>();
    }

    public boolean isMain() {
        return functions.get("main") != null;
    }

    public Module defineFunction(String name, Function function) throws OverdefinedException {
        if (this.functions.get(name) != null) {
            throw new OverdefinedException();
        }
        this.functions.put(name, function);
        return this;
    }

    public Function getFunction(String name) throws UndefinedException {
        Function function = this.functions.get(name);
        if (function == null) {
            throw new UndefinedException();
        }
        return function;
    }

    public Module addVar(DefInstruction instruction) {
        this.globalVariables.add(instruction);
        return this;
    }

    public DefInstruction[] getVars() {
        return this.globalVariables.toArray(new DefInstruction[0]);
    }
}
