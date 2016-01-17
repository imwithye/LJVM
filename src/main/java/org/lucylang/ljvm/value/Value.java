package org.lucylang.ljvm.value;

public abstract class Value {
    public abstract Integer intValue() throws ValueUnavailableException;

    public abstract Double floatValue() throws ValueUnavailableException;

    public abstract String stringValue() throws ValueUnavailableException;

    public abstract Boolean booleanValue() throws ValueUnavailableException;
}
