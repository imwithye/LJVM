package org.lucylang.ljvm.value;

import org.lucylang.ljvm.type.ArrayType;
import org.lucylang.ljvm.type.NumberType;
import org.lucylang.ljvm.type.Type;
import org.lucylang.ljvm.type.TypeUnmatchedException;

import java.util.ArrayList;

public class ArrayValue extends Value {
    private ArrayType arrayType;
    private Value[] values;

    public ArrayValue(int size) {
        this.arrayType = new ArrayType();
        if (size < 0) size = 0;
        this.values = new Value[size];
        for(int i=0; i<size; i++) {
            this.values[i] = new NoneValue();
        }
    }

    @Override
    public Type getType() {
        return arrayType;
    }

    @Override
    public Integer intValue() throws ValueUnavailableException {
        throw new ValueUnavailableException("int", this);
    }

    @Override
    public Double floatValue() throws ValueUnavailableException {
        throw new ValueUnavailableException("float", this);
    }

    @Override
    public String stringValue() throws ValueUnavailableException {
        ArrayList<String> v = new ArrayList<String>();
        for(int i=0; i<values.length; i++) {
            v.add(values[i].stringValue());
        }
        return "[" + String.join(", ", v) + "]";
    }

    @Override
    public Boolean booleanValue() throws ValueUnavailableException {
        throw new ValueUnavailableException("boolean", this);
    }

    @Override
    public Value add(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        if (!this.isSameType(value)) {
            throw new TypeUnmatchedException(this.getType(), value.getType());
        }
        ArrayValue arrayValue = (ArrayValue) value;
        ArrayValue newArrayValue = new ArrayValue(this.values.length + arrayValue.values.length);
        for (int i = 0; i < this.values.length; i++) {
            newArrayValue.values[i] = this.values[i];
        }
        for (int i = 0; i < arrayValue.values.length; i++) {
            newArrayValue.values[i + this.values.length] = this.values[i];
        }
        return newArrayValue;
    }

    @Override
    public Value sub(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        throw new ValueUnavailableException("unable to perform sub over " + this + " value");
    }

    @Override
    public Value mul(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        throw new ValueUnavailableException("unable to perform mul over " + this + " value");
    }

    @Override
    public Value div(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        throw new ValueUnavailableException("unable to perform div over " + this + " value");
    }

    @Override
    public Value and(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        throw new ValueUnavailableException("unable to perform and over " + this + " value");
    }

    @Override
    public Value or(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        throw new ValueUnavailableException("unable to perform or over " + this + " value");
    }

    @Override
    public Value not() throws ValueUnavailableException {
        throw new ValueUnavailableException("unable to perform not over " + this + " value");
    }

    @Override
    public BooleanValue equ(Value value) {
        if (value instanceof ArrayValue) {
            ArrayValue arrayValue = (ArrayValue) value;
            if (values.length != arrayValue.values.length) {
                return new BooleanValue(false);
            }
            for (int i = 0; i < values.length; i++) {
                if (!(values[i].equ(arrayValue.values[i])).booleanValue()) {
                    return new BooleanValue(false);
                }
            }
            return new BooleanValue(true);
        }
        return new BooleanValue(false);
    }

    @Override
    public BooleanValue les(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        throw new ValueUnavailableException("unable to perform les over " + this + " value");
    }

    @Override
    public BooleanValue gre(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        throw new ValueUnavailableException("unable to perform gre over " + this + " value");
    }

    @Override
    public BooleanValue leq(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        throw new ValueUnavailableException("unable to perform leq over " + this + " value");
    }

    @Override
    public BooleanValue geq(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        throw new ValueUnavailableException("unable to perform geq over " + this + " value");
    }

    @Override
    public Value valueAt(Value value) throws TypeUnmatchedException {
        if (value instanceof NumberValue) {
            return this.values[((NumberValue) value).intValue()];
        }
        throw new TypeUnmatchedException(new NumberType(), value.getType());
    }

    @Override
    public void set(Value index, Value value) throws TypeUnmatchedException {
        if (!(index instanceof NumberValue)) {
            throw new TypeUnmatchedException(new NumberType(), index.getType());
        }
        this.values[((NumberValue) index).intValue()] = value;
    }

    @Override
    public NumberValue length() throws ValueUnavailableException {
        return new NumberValue(this.values.length);
    }

    @Override
    public Value copy() {
        ArrayValue arrayValue = new ArrayValue(this.values.length);
        for (int i = 0; i < values.length; i++) {
            if(this.values[i] == null) {
                System.out.println(i);
            }
            arrayValue.values[i] = this.values[i].copy();
        }
        return arrayValue;
    }
}
