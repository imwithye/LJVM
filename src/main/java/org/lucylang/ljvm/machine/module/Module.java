package org.lucylang.ljvm.machine.module;

import org.lucylang.ljvm.scope.OverdefinedException;
import org.lucylang.ljvm.scope.UndefinedException;

import java.util.HashMap;

public class Module {
    private HashMap<String, Function> functions;

    public Module() {
        this.functions = new HashMap<String, Function>();
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
}
