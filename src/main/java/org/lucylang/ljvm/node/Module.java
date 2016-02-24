package org.lucylang.ljvm.node;

import java.util.ArrayList;

public class Module extends Node {
    private ArrayList<Function> functions;

    public Module() {
        this.functions = new ArrayList<Function>();
    }

    public Module addFunction(Function function) {
        this.functions.add(function);
        return this;
    }

    public ArrayList<Function> getFunctions() {
        return this.functions;
    }
}
