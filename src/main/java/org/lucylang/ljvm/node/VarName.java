package org.lucylang.ljvm.node;

public class VarName implements IValue {
    private String varName;

    public VarName(String varName) {
        assert varName != null;
        this.varName = varName;
    }

    public String getVarName() {
        return this.varName;
    }
}
