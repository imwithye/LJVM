package org.lucylang.ljvm.node;

import java.util.ArrayList;

public class Call extends Node implements IStmt, IValue {
    private VarName funcName;
    private ArrayList<IValue> parameters;

    public Call(VarName funcName) {
        assert funcName != null;
        this.funcName = funcName;
        this.parameters = new ArrayList<IValue>();
    }

    public Call addParameter(IValue parameter) {
        assert parameter != null;
        this.parameters.add(parameter);
        return this;
    }

    public VarName getFuncName() {
        return this.funcName;
    }

    public ArrayList<IValue> getParameters() {
        return this.parameters;
    }
}
