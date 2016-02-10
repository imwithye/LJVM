package org.lucylang.ljvm.node;

import java.util.ArrayList;

public class IfElse extends Node implements IStmt {
    private BinaryExpr expr;
    private ArrayList<IStmt> ifNodes;
    private ArrayList<IStmt> elseNodes;

    public IfElse(BinaryExpr expr) {
        assert expr != null;
        this.expr = expr;
        this.ifNodes = new ArrayList<IStmt>();
        this.elseNodes = new ArrayList<IStmt>();
    }

    public IfElse(BooleanLiteral booleanLiteral) {
        assert booleanLiteral != null;
        this.expr = new AndExpr(booleanLiteral, new BooleanLiteral(true));
        this.ifNodes = new ArrayList<IStmt>();
        this.elseNodes = new ArrayList<IStmt>();
    }

    public IfElse setExpr(BinaryExpr expr) {
        assert expr != null;
        this.expr = expr;
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

    public BinaryExpr getExpr() {
        return this.expr;
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
