package org.lucylang.ljvm.type;

public class StringType extends Type {
    @Override
    public boolean equals(Object type) {
        if (type instanceof StringType) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "StringType";
    }
}