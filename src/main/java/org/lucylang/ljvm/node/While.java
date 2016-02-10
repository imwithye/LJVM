package org.lucylang.ljvm.node;

import java.util.ArrayList;

public class While extends Node implements IStmt {
    private IValue value;
    private ArrayList<IStmt> stmts;

    public While(IValue value) {
        assert value != null;
        this.value = value;
        this.stmts = new ArrayList<IStmt>();
    }

    public While addStmt(IStmt stmt) {
        this.stmts.add(stmt);
        return this;
    }

    public IValue getValue() {
        return this.value;
    }

    public ArrayList<IStmt> getStmts() {
        return this.stmts;
    }
}
