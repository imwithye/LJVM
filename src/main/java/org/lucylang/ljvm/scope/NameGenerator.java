package org.lucylang.ljvm.scope;

import java.io.Serializable;

public class NameGenerator implements Serializable {
    private int counter;
    private Class theClass;

    public NameGenerator(final Class theClass) {
        this(null, theClass);
    }

    public NameGenerator(NameGenerator parent, final Class theClass) {
        assert theClass != null;
        this.theClass = theClass;
    }

    public String getAnonymousName() {
        String name = this.theClass.getSimpleName() + "$" + "<" + "anonymous$" + counter + ">";
        this.counter++;
        return name;
    }

    public String getName(String name) {
        assert name != null;
        return this.theClass.getSimpleName() + "$" + "<" + name + ">";
    }
}
