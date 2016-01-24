package org.lucylang.ljvm.type;

import java.io.Serializable;

public class Type implements Serializable {
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
