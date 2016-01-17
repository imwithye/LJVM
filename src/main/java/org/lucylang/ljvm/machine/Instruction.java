package org.lucylang.ljvm.machine;

import org.lucylang.ljvm.value.NumberValue;
import org.lucylang.ljvm.value.StringValue;
import org.lucylang.ljvm.value.Value;

public class Instruction {
    private InstructionType type;
    private boolean[] isRef;
    private String[] ids;
    private Value[] values;

    public static Instruction create(InstructionType type) {
        Instruction i = new Instruction();
        i.type = type;
        i.isRef = new boolean[3];
        i.ids = new String[3];
        i.values = new Value[3];
        return i;
    }

    private static boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    private static boolean isValid(String string) {
        return true;
    }

    private static boolean isRef(String string) {
        if (!string.contains("\"") && !isNumber(string)) {
            return true;
        } else {
            return false;
        }
    }

    private static void setInstruction(Instruction i, String first) throws InvalidInstructionException {
        if (!isValid(first)) {
            throw new InvalidInstructionException();
        }
        if (isRef(first)) {
            i.first(first);
        } else if (isNumber(first)) {
            i.first(new NumberValue(Double.parseDouble(first)));
        } else {
            i.first(new StringValue(first.substring(1, first.length() - 1)));
        }
    }

    private static void setInstruction(Instruction i, String first, String second) throws InvalidInstructionException {
        if (!isValid(first) || !isValid(second)) {
            throw new InvalidInstructionException();
        }
        if (isRef(first)) {
            i.first(first);
        } else if (isNumber(first)) {
            i.first(new NumberValue(Double.parseDouble(first)));
        } else {
            i.first(new StringValue(first.substring(1, first.length() - 1)));
        }
        if (isRef(second)) {
            i.second(second);
        } else if (isNumber(second)) {
            i.second(new NumberValue(Double.parseDouble(second)));
        } else {
            i.second(new StringValue(second.substring(1, second.length() - 1)));
        }
    }

    private static void setInstruction(Instruction i, String first, String second, String third) throws InvalidInstructionException {
        if (!isValid(first) || !isValid(second) || !isValid(third)) {
            throw new InvalidInstructionException();
        }
        if (isRef(first)) {
            i.first(first);
        } else if (isNumber(first)) {
            i.first(new NumberValue(Double.parseDouble(first)));
        } else {
            i.first(new StringValue(first.substring(1, first.length() - 1)));
        }
        if (isRef(second)) {
            i.second(second);
        } else if (isNumber(second)) {
            i.second(new NumberValue(Double.parseDouble(second)));
        } else {
            i.second(new StringValue(second.substring(1, second.length() - 1)));
        }
        if (isRef(third)) {
            i.third(third);
        } else if (isNumber(third)) {
            i.third(new NumberValue(Double.parseDouble(third)));
        } else {
            i.third(new StringValue(third.substring(1, third.length() - 1)));
        }
    }

    public static Instruction create(String instruction) throws InvalidInstructionException {
        instruction = instruction.trim().replaceAll(" +", " ");
        String[] splits = instruction.split(" ");
        if (splits.length > 4 || splits.length < 1) {
            throw new InvalidInstructionException();
        }
        String type = splits[0].toUpperCase();
        Instruction i = new Instruction();
        i.isRef = new boolean[3];
        i.ids = new String[3];
        i.values = new Value[3];
        if (type.equals("NULL")) {
            i.type = InstructionType.NULL;
        } else if (type.equals("DEFINE")) {
            if (splits.length != 3) {
                throw new InvalidInstructionException();
            }
            i.type = InstructionType.DEFINE;
            setInstruction(i, splits[1], splits[2]);
        } else if (type.equals("MV")) {
            if (splits.length != 3) {
                throw new InvalidInstructionException();
            }
            i.type = InstructionType.MV;
            setInstruction(i, splits[1], splits[2]);
        } else if (type.equals("ADD")) {
            if (splits.length != 4) {
                throw new InvalidInstructionException();
            }
            i.type = InstructionType.ADD;
            setInstruction(i, splits[1], splits[2], splits[3]);
        } else if (type.equals("SUB")) {
            if (splits.length != 4) {
                throw new InvalidInstructionException();
            }
            i.type = InstructionType.SUB;
            setInstruction(i, splits[1], splits[2], splits[3]);
        } else if (type.equals("MUL")) {
            if (splits.length != 4) {
                throw new InvalidInstructionException();
            }
            i.type = InstructionType.MUL;
            setInstruction(i, splits[1], splits[2], splits[3]);
        } else if (type.equals("DIV")) {
            if (splits.length != 4) {
                throw new InvalidInstructionException();
            }
            i.type = InstructionType.DIV;
            setInstruction(i, splits[1], splits[2], splits[3]);
        } else if (type.equals("AND")) {
            if (splits.length != 4) {
                throw new InvalidInstructionException();
            }
            i.type = InstructionType.AND;
            setInstruction(i, splits[1], splits[2], splits[3]);
        } else if (type.equals("OR")) {
            if (splits.length != 4) {
                throw new InvalidInstructionException();
            }
            i.type = InstructionType.OR;
            setInstruction(i, splits[1], splits[2], splits[3]);
        } else if (type.equals("NOT")) {
            if (splits.length != 4) {
                throw new InvalidInstructionException();
            }
            i.type = InstructionType.NOT;
            setInstruction(i, splits[1], splits[2]);
        } else if (type.equals("GOTO")) {
            i.type = InstructionType.GOTO;
        } else {
            throw new InvalidInstructionException();
        }
        return i;
    }

    public Instruction first(String id) {
        this.isRef[0] = true;
        this.ids[0] = id;
        return this;
    }

    public Instruction first(Value value) {
        this.isRef[0] = false;
        this.values[0] = value;
        return this;
    }

    public Instruction second(String id) {
        this.isRef[1] = true;
        this.ids[1] = id;
        return this;
    }

    public Instruction second(Value value) {
        this.isRef[1] = false;
        this.values[1] = value;
        return this;
    }

    public Instruction third(String id) {
        this.isRef[2] = true;
        this.ids[2] = id;
        return this;
    }

    public Instruction third(Value value) {
        this.isRef[2] = false;
        this.values[2] = value;
        return this;
    }

    public boolean isRef(int pos) {
        return this.isRef[pos];
    }

    public String getRef(int pos) {
        return this.ids[pos];
    }

    public Value getValue(int pos) {
        return this.values[pos];
    }

    public InstructionType getType() {
        return this.type;
    }
}
