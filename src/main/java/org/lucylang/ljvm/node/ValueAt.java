package org.lucylang.ljvm.node;

public class ValueAt extends Node implements IValue {
    private VarName varName;
    private IValue index;

    public ValueAt(VarName varName, IValue index) {
        assert varName != null;
        assert index != null;
        this.varName = varName;
        this.index = index;
    }

    public VarName getVarName() {
        return this.varName;
    }

    public IValue getIndex() {
        return this.index;
    }
}
