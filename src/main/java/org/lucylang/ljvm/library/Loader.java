package org.lucylang.ljvm.library;

import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.scope.Scope;
import org.lucylang.ljvm.scope.UndefinedException;

public class Loader {
    private static Loader loader;
    private static Scope<String, Module> systemModule;

    private Loader() {
    }

    public static Loader getLoader() {
        if (loader == null) {
            loader = new Loader();
            systemModule = new Scope<String, Module>();
            systemModule.set("std", new Std());
            systemModule.set("math", new Math());
        }
        return loader;
    }

    public Module getModule(String moduleName) throws UndefinedException {
        return systemModule.safeGet(moduleName);
    }
}
