package org.lucylang.ljvm.library;

import org.lucylang.ljvm.machine.instruction.*;
import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.machine.module.Routine;

public class Std extends Module {
    public Std() {
        super("std");
        Routine input = new Routine(new Instruction[]{
                new GetInstruction(new RefOperand("value")),
                new PushInstruction(new RefOperand("value")),
                new RetInstruction()
        });
        this.routines.set("input", input);
        Routine print = new Routine(new Instruction[]{
                new PopInstruction(new RefOperand("value")),
                new PutInstruction(new RefOperand("value"))
        });
        this.routines.set("print", print);
        Routine string = new Routine(new Instruction[]{
                new PopInstruction(new RefOperand("value")),
                new StrInstruction(new RefOperand("$1"), new RefOperand("value")),
                new PushInstruction(new RefOperand("$1")),
                new RetInstruction()
        });
        this.routines.set("string", string);
        Routine number = new Routine(new Instruction[]{
                new PopInstruction(new RefOperand("value")),
                new NumInstruction(new RefOperand("$1"), new RefOperand("value")),
                new PushInstruction(new RefOperand("$1")),
                new RetInstruction()
        });
        this.routines.set("number", number);
        Routine bool = new Routine(new Instruction[]{
                new PopInstruction(new RefOperand("value")),
                new BoolInstruction(new RefOperand("$1"), new RefOperand("value")),
                new PushInstruction(new RefOperand("$1")),
                new RetInstruction()
        });
        this.routines.set("boolean", bool);
    }
}
