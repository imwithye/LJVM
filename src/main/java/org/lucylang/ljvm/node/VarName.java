package org.lucylang.ljvm.node;

public class VarName extends Node implements IValue {
    private String varName;

    public VarName(String varName) {
        assert varName != null;
        this.varName = varName;
    }

    public String getVarName() {
        return this.varName;
    }

    @Override
    public String toString() {
        return "VarName<" + varName + ">";
    }
}
