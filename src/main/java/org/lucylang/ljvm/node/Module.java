package org.lucylang.ljvm.node;

import java.util.ArrayList;

public class Module extends Node {
    private final String name;
    private ArrayList<String> moduleNames;
    private ArrayList<Function> functions;

    public Module(String name) {
        this.name = name;
        this.moduleNames = new ArrayList<String>();
        this.functions = new ArrayList<Function>();
    }

    public Module addImport(String moduleName) {
        moduleNames.add(moduleName);
        return this;
    }

    public Module addFunction(Function function) {
        this.functions.add(function);
        return this;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<String> getImports() {
        return this.moduleNames;
    }

    public ArrayList<Function> getFunctions() {
        return this.functions;
    }
}
