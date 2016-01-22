package org.lucylang.ljvm.machine.generator;

import org.lucylang.ljvm.machine.module.Module;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Generator {
    public void generate(Module module, FileOutputStream fileOutputStream) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
        oos.writeObject(module);
        oos.close();
    }
}
