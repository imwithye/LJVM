package org.lucylang.ljvm.node;

public class Assignment implements IStmt {
    private VarName varName;
    private IValue expr;

    public Assignment(VarName varName, IValue expr) {
        assert varName != null;
        assert expr != null;
        this.varName = varName;
        this.expr = expr;
    }

    public VarName getVarName() {
        return this.varName;
    }

    public IValue getExpr() {
        return this.expr;
    }
}
