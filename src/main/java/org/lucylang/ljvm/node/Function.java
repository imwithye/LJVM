package org.lucylang.ljvm.node;

import java.util.ArrayList;

public class Function {
    private String funcName;
    private ArrayList<VarName> parameters;
    private ArrayList<IStmt> stmts;

    public Function(String funcName) {
        assert funcName != null;
        this.funcName = funcName;
        this.parameters = new ArrayList<VarName>();
        this.stmts = new ArrayList<IStmt>();
    }

    public Function addParameter(VarName parameter) {
        assert parameter != null;
        this.parameters.add(parameter);
        return this;
    }

    public Function addStmt(IStmt stmt) {
        assert stmt != null;
        this.stmts.add(stmt);
        return this;
    }

    public String getFuncName() {
        return this.funcName;
    }

    public ArrayList<VarName> getParameters() {
        return this.parameters;
    }

    public ArrayList<IStmt> getStmts() {
        return this.stmts;
    }
}
