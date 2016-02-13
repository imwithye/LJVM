package org.lucylang.ljvm.type;

public class NoneType extends Type {
    @Override
    public boolean equals(Object type) {
        if (type instanceof NoneType) {
            return true;
        }
        return false;
    }
}
