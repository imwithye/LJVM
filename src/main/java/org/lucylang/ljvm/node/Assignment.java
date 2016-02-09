package org.lucylang.ljvm.node;

public class Assignment implements IStmt {
    private VarName varName;
    private BinaryExpr expr;

    public Assignment(VarName varName, BinaryExpr expr) {
        assert varName != null;
        assert expr != null;
        this.varName = varName;
        this.expr = expr;
    }

    public VarName getVarName() {
        return this.varName;
    }

    public BinaryExpr getExpr() {
        return this.expr;
    }
}
