package org.lucylang.ljvm.type;

public class BooleanType extends Type {
    @Override
    public boolean equals(Object type) {
        if (type instanceof BooleanType) {
            return true;
        }
        return false;
    }
}
