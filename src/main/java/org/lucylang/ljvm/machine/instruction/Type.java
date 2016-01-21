package org.lucylang.ljvm.machine.instruction;

public enum Type {
    NULL, DEF, MOV, ADD, SUB, MUL, DIV, AND, OR, NOT,
    PUSH, POP, PEEK,
    GOTO, BEQ, BNE,
    NUM, STR, BOOL,
    PUT
}
