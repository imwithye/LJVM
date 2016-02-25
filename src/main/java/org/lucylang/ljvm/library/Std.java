package org.lucylang.ljvm.library;

import org.lucylang.ljvm.machine.instruction.*;
import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.machine.module.Routine;

public class Std extends Module {
    public Std() {
        super("std");
        this.routines.set("input", new Routine(new Instruction[]{
                new GetInstruction(new RefOperand("value")),
                new PushInstruction(new RefOperand("value")),
                new RetInstruction()
        }));
        this.routines.set("print", new Routine(new Instruction[]{
                new PopInstruction(new RefOperand("value")),
                new PutInstruction(new RefOperand("value"))
        }));
        this.routines.set("string", new Routine(new Instruction[]{
                new PopInstruction(new RefOperand("value")),
                new StrInstruction(new RefOperand("$1"), new RefOperand("value")),
                new PushInstruction(new RefOperand("$1")),
                new RetInstruction()
        }));
        this.routines.set("number", new Routine(new Instruction[]{
                new PopInstruction(new RefOperand("value")),
                new NumInstruction(new RefOperand("$1"), new RefOperand("value")),
                new PushInstruction(new RefOperand("$1")),
                new RetInstruction()
        }));
        this.routines.set("int", new Routine(new Instruction[]{
                new PopInstruction(new RefOperand("n")),
                new IntInstruction(new RefOperand("n"), new RefOperand("n")),
                new PushInstruction(new RefOperand("n")),
                new RetInstruction()
        }));
        this.routines.set("boolean", new Routine(new Instruction[]{
                new PopInstruction(new RefOperand("value")),
                new BoolInstruction(new RefOperand("$1"), new RefOperand("value")),
                new PushInstruction(new RefOperand("$1")),
                new RetInstruction()
        }));
    }
}
