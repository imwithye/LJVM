package org.lucylang.ljvm.generator;

import org.lucylang.ljvm.machine.instruction.InvalidInstruction;
import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.machine.module.Routine;
import org.lucylang.ljvm.node.Function;
import org.lucylang.ljvm.scope.OverdefinedException;
import org.lucylang.ljvm.scope.UndefinedException;

import java.util.ArrayList;

public class ModuleCodeGenerator {
    public Module visitModule(String name, org.lucylang.ljvm.node.Module module) throws OverdefinedException, UndefinedException, InvalidInstruction {
        Module m = new Module(name);
        ArrayList<String> imports = module.getImports();
        for (int i = 0; i < imports.size(); i++) {
            m.imports(imports.get(i));
        }
        FuncCodeGenerator funcCodeGenerator = new FuncCodeGenerator();
        ArrayList<Function> functions = module.getFunctions();
        for (int i = 0; i < functions.size(); i++) {
            Function function = functions.get(i);
            Routine routine = funcCodeGenerator.visitFunction(function);
            String funcName = function.getFuncName();
            m.defineRoutine(funcName, routine);
        }
        return m;
    }
}
