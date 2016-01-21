package org.lucylang.ljvm.machine;

import org.lucylang.ljvm.machine.instruction.Instruction;
import org.lucylang.ljvm.machine.instruction.InvalidInstruction;
import org.lucylang.ljvm.machine.module.Function;
import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.scope.OverdefinedException;
import org.lucylang.ljvm.scope.Scope;
import org.lucylang.ljvm.scope.UndefinedException;
import org.lucylang.ljvm.type.TypeUnmatchedException;
import org.lucylang.ljvm.value.Value;
import org.lucylang.ljvm.value.ValueUnavailableException;

import java.util.HashMap;
import java.util.Stack;

public class Machine {
    private int next;
    private Scope<String, Register> registers;
    private HashMap<String, Module> modules;
    private HashMap<Module, String> moduleNames;
    private Module mainModule;
    private Module executingModule;
    private Function executingFunction;
    private String executingModuleName;
    private String executingFunctionName;
    private Stack<Value> memoryStack;

    public Machine() {
        this.reset();
    }

    public Machine reset() {
        this.next = 0;
        this.registers = new Scope<String, Register>();
        this.modules = new HashMap<String, Module>();
        this.moduleNames = new HashMap<Module, String>();
        this.mainModule = null;
        this.memoryStack = new Stack<Value>();
        this.executingModule = null;
        this.executingModuleName = null;
        this.executingFunction = null;
        this.executingFunctionName = null;
        return this;
    }

    public Machine setNext(int next) {
        this.next = next;
        return this;
    }

    public int getNext() {
        return this.next;
    }


    public Module getExecutingModule() {
        return this.executingModule;
    }

    public String getExecutingModuleName() {
        return this.executingModuleName;
    }

    public Function getExecutingFunction() {
        return this.getExecutingFunction();
    }

    public String getExecutingFunctionName() {
        return this.executingFunctionName;
    }

    public Register getRegister(String ref) throws UndefinedException {
        Register r = this.registers.get(ref);
        if (r == null) {
            throw new UndefinedException();
        }
        return r;
    }

    public Value getValue(String ref) throws UndefinedException {
        return this.getRegister(ref).getValue();
    }

    public Machine assignRegister(String ref, Value value) throws UndefinedException {
        this.getRegister(ref).assignValue(value);
        return this;
    }

    public Machine defineRegister(String ref, Value value) throws OverdefinedException {
        if (!this.registers.isDefined(ref)) {
            this.registers.put(ref, new Register(value));
        } else {
            throw new OverdefinedException();
        }
        return this;
    }

    public Module getModule(String name) throws UndefinedException {
        Module module = this.modules.get(name);
        if (module == null) {
            throw new UndefinedException();
        }
        return module;
    }

    public Machine importModule(String name, Module module) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        if (this.modules.get(name) != null) {
            throw new OverdefinedException();
        }
        if (module.isMain()) {
            if (this.mainModule != null) {
                throw new OverdefinedException();
            }
            this.mainModule = module;
        }
        this.modules.put(name, module);
        this.moduleNames.put(module, name);
        this.execute(module.getVars(), module, name, null, null);
        return this;
    }

    public Machine pushValue(Value value) {
        this.memoryStack.push(value);
        return this;
    }

    public Machine popValue() {
        this.memoryStack.pop();
        return this;
    }

    public Value peek() throws UndefinedException {
        if (this.memoryStack.size() == 0) {
            throw new UndefinedException();
        }
        return this.memoryStack.peek();
    }

    public Machine execute(Instruction[] instructions, Module executingModule, String executingModuleName, Function executingFunction, String executingFunctionName) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        this.executingModule = executingModule;
        this.executingModuleName = executingModuleName;
        this.executingFunction = executingFunction;
        this.executingFunctionName = executingFunctionName;
        this.next = 0;
        while (this.next < instructions.length) {
            Instruction i = instructions[this.next];
            i.execute(this);
            this.next++;
        }
        return this;
    }

    public Machine execute(Function function, String executingFunctionName, Module executingModule, String executingModuleName) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        Scope<String, Register> current = this.registers;
        Scope<String, Register> functionScope = new Scope<String, Register>(current);
        this.registers = functionScope;
        this.execute(function.getInstructions(), executingModule, executingModuleName, executingFunction, executingFunctionName);
        this.registers = current;
        return this;
    }

    public Machine execute(Module module, String executingModuleName) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        Function function = module.getFunction("main");
        return this.execute(function, "main", module, executingModuleName);
    }

    public Machine execute() throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        if (this.mainModule == null) {
            throw new UndefinedException();
        }
        this.executingModuleName = this.moduleNames.get(this.mainModule);
        return this.execute(this.mainModule, this.executingModuleName);
    }

    public Machine call(String moduleName, String functionName) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        Module targetModule = this.getModule(moduleName);
        Function targetFunction = targetModule.getFunction(functionName);
        return this.execute(targetFunction, functionName, targetModule, moduleName);
    }

    public Machine call(String functionName) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        return this.call(this.executingModuleName, functionName);
    }
}
