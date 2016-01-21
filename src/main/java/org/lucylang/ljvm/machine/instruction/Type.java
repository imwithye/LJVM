package org.lucylang.ljvm.machine.instruction;

public enum Type {
    NULL, DEF, MOV, ADD, SUB, MUL, DIV, AND, OR, NOT,
    PUSH, POP, PEEK, CALL, RET,
    GOTO, BEQ, BNE,
    NUM, STR, BOOL,
    PUT
}
