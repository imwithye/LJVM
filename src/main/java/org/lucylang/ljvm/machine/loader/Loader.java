package org.lucylang.ljvm.machine.loader;

import org.lucylang.ljvm.machine.module.Module;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Loader {
    public Module load(FileInputStream fileInputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(fileInputStream);
        Module module = (Module) ois.readObject();
        ois.close();
        return module;
    }
}
