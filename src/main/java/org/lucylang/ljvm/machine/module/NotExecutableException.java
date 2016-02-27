package org.lucylang.ljvm.machine.module;

import org.lucylang.ljvm.exception.RuntimeException;

public class NotExecutableException extends RuntimeException {
    public NotExecutableException(Module m) {
        super("Module " + m.getName() + " is not executable");
    }

    public NotExecutableException() {
        super("main function can only be defined in main package");
    }
}
