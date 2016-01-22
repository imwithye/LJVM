package org.lucylang.ljvm.machine.driver;

import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.machine.generator.Generator;
import org.lucylang.ljvm.machine.loader.Loader;
import org.lucylang.ljvm.machine.module.Module;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Driver {
    private Loader loader;
    private Generator generator;

    public Driver() {
        this.loader = new Loader();
        this.generator = new Generator();
    }

    public Module loadModule(FileInputStream fis) {
        try {
            return this.loader.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    public void generateModule(Module module, FileOutputStream fos) throws IOException {
        try {
            this.generator.generate(module, fos);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public Machine initVM() {
        return new Machine();
    }
}
