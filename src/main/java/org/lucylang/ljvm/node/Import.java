package org.lucylang.ljvm.node;

public class Import extends Node {
    private String moduleName;

    public Import(String moduleName) {
        assert moduleName != null;
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return this.moduleName;
    }
}
