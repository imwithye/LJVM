package org.lucylang.ljvm.machine;

import org.lucylang.ljvm.value.NumberValue;
import org.lucylang.ljvm.value.StringValue;
import org.lucylang.ljvm.value.Value;

public class InstructionParser {
    private boolean isNumber(String string) {
        return string.matches("[0-9]*[.]?[0-9]*");
    }

    private boolean isValid(String string) {
        return isNumber(string) || isRef(string) || (string.charAt(0) == '"' && string.charAt(string.length() - 1) == '"');
    }

    private boolean isRef(String string) {
        return string.matches("[a-zA-Z]+[a-zA-Z0-9_]*");
    }

    private void setInstruction(Instruction i, String first) throws InvalidInstructionException {
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

    private void setInstruction(Instruction i, String first, String second) throws InvalidInstructionException {
        setInstruction(i, first);
        if (isRef(second)) {
            i.second(second);
        } else if (isNumber(second)) {
            i.second(new NumberValue(Double.parseDouble(second)));
        } else {
            i.second(new StringValue(second.substring(1, second.length() - 1)));
        }
    }

    private void setInstruction(Instruction i, String first, String second, String third) throws InvalidInstructionException {
        setInstruction(i, first, second);
        if (isRef(third)) {
            i.third(third);
        } else if (isNumber(third)) {
            i.third(new NumberValue(Double.parseDouble(third)));
        } else {
            i.third(new StringValue(third.substring(1, third.length() - 1)));
        }
    }

    public Instruction parse(String instruction) throws InvalidInstructionException {
        instruction = instruction.trim().replaceAll(" +", " ");
        String[] splits = instruction.split(" ");
        if (splits.length > 4 || splits.length < 1) {
            throw new InvalidInstructionException();
        }
        String type = splits[0].toUpperCase();
        Instruction i;
        if (type.equals("NULL")) {
            i = Instruction.create(InstructionType.NULL);
        } else if (type.equals("DEFINE")) {
            if (splits.length != 3) {
                throw new InvalidInstructionException();
            }
            i = Instruction.create(InstructionType.DEFINE);
            setInstruction(i, splits[1], splits[2]);
        } else if (type.equals("MV")) {
            if (splits.length != 3) {
                throw new InvalidInstructionException();
            }
            i = Instruction.create(InstructionType.MV);
            setInstruction(i, splits[1], splits[2]);
        } else if (type.equals("ADD")) {
            if (splits.length != 4) {
                throw new InvalidInstructionException();
            }
            i = Instruction.create(InstructionType.ADD);
            setInstruction(i, splits[1], splits[2], splits[3]);
        } else if (type.equals("SUB")) {
            if (splits.length != 4) {
                throw new InvalidInstructionException();
            }
            i = Instruction.create(InstructionType.SUB);
            setInstruction(i, splits[1], splits[2], splits[3]);
        } else if (type.equals("MUL")) {
            if (splits.length != 4) {
                throw new InvalidInstructionException();
            }
            i = Instruction.create(InstructionType.MUL);
            setInstruction(i, splits[1], splits[2], splits[3]);
        } else if (type.equals("DIV")) {
            if (splits.length != 4) {
                throw new InvalidInstructionException();
            }
            i = Instruction.create(InstructionType.DIV);
            setInstruction(i, splits[1], splits[2], splits[3]);
        } else if (type.equals("AND")) {
            if (splits.length != 4) {
                throw new InvalidInstructionException();
            }
            i = Instruction.create(InstructionType.AND);
            setInstruction(i, splits[1], splits[2], splits[3]);
        } else if (type.equals("OR")) {
            if (splits.length != 4) {
                throw new InvalidInstructionException();
            }
            i = Instruction.create(InstructionType.OR);
            setInstruction(i, splits[1], splits[2], splits[3]);
        } else if (type.equals("NOT")) {
            if (splits.length != 3) {
                throw new InvalidInstructionException();
            }
            i = Instruction.create(InstructionType.NOT);
            setInstruction(i, splits[1], splits[2]);
        } else if (type.equals("GOTO")) {
            i = Instruction.create(InstructionType.GOTO);
        } else {
            throw new InvalidInstructionException();
        }
        return i;
    }
}
