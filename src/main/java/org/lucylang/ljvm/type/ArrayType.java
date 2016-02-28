package org.lucylang.ljvm.type;

public class ArrayType extends Type {
    @Override
    public boolean equals(Object type) {
        if (type instanceof ArrayType) {
            return true;
        }
        return false;
    }
}
