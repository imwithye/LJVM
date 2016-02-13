package org.lucylang.ljvm.generator;

import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.machine.module.Routine;
import org.lucylang.ljvm.node.Function;
import org.lucylang.ljvm.scope.OverdefinedException;

import java.util.ArrayList;

public class ModuleCodeGenerator {
    public Module visitModule(org.lucylang.ljvm.node.Module module) throws OverdefinedException {
        Module m = new Module();
        FuncCodeGenerator funcCodeGenerator = new FuncCodeGenerator();
        ArrayList<Function> functions = module.getFunctions();
        for (int i = 0; i < functions.size(); i++) {
            Function function = functions.get(i);
            Routine routine = funcCodeGenerator.visitFunction(function);
            String name = function.getFuncName();
            m.defineRoutine(name, routine);
        }
        return m;
    }
}
