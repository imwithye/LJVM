package org.lucylang.ljvm.node;

import java.util.ArrayList;

public class While extends Node implements IStmt {
    private BinaryExpr expr;
    private ArrayList<IStmt> stmts;

    public While(BinaryExpr expr) {
        assert expr != null;
        this.expr = expr;
        this.stmts = new ArrayList<IStmt>();
    }

    public While(BooleanLiteral booleanLiteral) {
        assert booleanLiteral != null;
        this.expr = new AndExpr(booleanLiteral, new BooleanLiteral(true));
        this.stmts = new ArrayList<IStmt>();
    }

    public While addStmt(IStmt stmt) {
        this.stmts.add(stmt);
        return this;
    }

    public BinaryExpr getExpr() {
        return this.expr;
    }

    public ArrayList<IStmt> getStmts() {
        return this.stmts;
    }
}
