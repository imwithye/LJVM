package org.lucylang.ljvm.type;

import org.lucylang.ljvm.exception.RuntimeException;

public class TypeUnmatchedException extends RuntimeException {
    private Type leftTy;
    private Type rightTy;

    public TypeUnmatchedException(Type leftTy, Type rightTy) {
        super("Type Unmatched Exception: <" + leftTy + "> and <" + rightTy + ">");
        this.leftTy = leftTy;
        this.rightTy = rightTy;
    }

    public Type getLeftType() {
        return this.leftTy;
    }

    public Type getRightType() {
        return this.rightTy;
    }
}
