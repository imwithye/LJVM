package org.lucylang.ljvm.node;

import java.util.ArrayList;

public class IfElse extends Node implements IStmt {
    private IValue value;
    private ArrayList<IStmt> ifNodes;
    private ArrayList<IStmt> elseNodes;

    public IfElse(IValue value) {
        assert value != null;
        this.value = value;
        this.ifNodes = new ArrayList<IStmt>();
        this.elseNodes = new ArrayList<IStmt>();
    }

    public IfElse setValue(BinaryExpr value) {
        assert value != null;
        this.value = value;
        return this;
    }

    public IfElse addStmtIf(IStmt node) {
        assert node != null;
        this.ifNodes.add(node);
        return this;
    }

    public IfElse addStmtElse(IStmt node) {
        assert node != null;
        this.elseNodes.add(node);
        return this;
    }

    public IValue getValue() {
        return this.value;
    }

    public ArrayList<IStmt> getIfStmts() {
        return this.ifNodes;
    }

    public ArrayList<IStmt> getElseStmts() {
        return this.elseNodes;
    }

    public boolean hasElse() {
        return this.elseNodes.size() > 0;
    }
}
