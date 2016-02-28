package org.lucylang.ljvm.node;

public class Assignment extends Node implements IStmt {
    private VarName varName;
    private ValueAt valueAt;
    private IValue expr;

    public Assignment(VarName varName, IValue expr) {
        assert varName != null;
        assert expr != null;
        this.varName = varName;
        this.expr = expr;
    }

    public Assignment(ValueAt valueAt, IValue expr) {
        assert valueAt != null;
        assert expr != null;
        this.valueAt = valueAt;
        this.expr = expr;
    }

    public boolean simpleAssign() {
        return this.varName != null;
    }

    public ValueAt getValueAt() {
        return this.valueAt;
    }

    public VarName getVarName() {
        return this.varName;
    }

    public IValue getExpr() {
        return this.expr;
    }
}
