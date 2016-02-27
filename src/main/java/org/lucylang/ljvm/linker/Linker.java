package org.lucylang.ljvm.linker;

import org.lucylang.ljvm.machine.module.Module;

import java.util.ArrayList;

public class Linker {
    public Module linkModules(String name, ArrayList<Module> modules) {
        Module m = new Module(name);
        for (int i = 0; i < modules.size(); i++) {
            Module module = modules.get(i);
        }
        return null;
    }
}
