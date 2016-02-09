package org.lucylang.ljvm.machine.module;

import org.json.JSONArray;
import org.json.JSONException;
import org.lucylang.ljvm.machine.instruction.DefInstruction;
import org.lucylang.ljvm.machine.instruction.Instruction;
import org.lucylang.ljvm.machine.instruction.RefOperand;
import org.lucylang.ljvm.machine.instruction.ValueOperand;
import org.lucylang.ljvm.scope.OverdefinedException;
import org.lucylang.ljvm.scope.UndefinedException;

import org.json.JSONObject;
import org.lucylang.ljvm.value.BooleanValue;
import org.lucylang.ljvm.value.NumberValue;
import org.lucylang.ljvm.value.StringValue;
import org.lucylang.ljvm.value.Value;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Module implements Serializable {
    private HashMap<String, Routine> routines;
    private ArrayList<DefInstruction> vars;

    public Module() {
        this.routines = new HashMap<String, Routine>();
        this.vars = new ArrayList<DefInstruction>();
    }
    
    public static Module loadFromJSON(JSONObject object) throws OverdefinedException {
        try {
            String name = object.getString("name");
            JSONArray vars = object.getJSONArray("vars");
            JSONArray routines = object.getJSONArray("routines");
            Module module = new Module();

            String varName;
            String varRef;
            Value varValue;
            for (int i = 0; i < vars.length(); i++) {
                JSONObject var = vars.getJSONObject(i);
                varName = var.getString("name");
                if (var.has("ref")) {
                    varRef = var.getString("ref");
                    module.addVar(new DefInstruction(new RefOperand(varName), new RefOperand(varRef)));
                } else if (var.has("value")) {
                    try {
                        varValue = new NumberValue(var.getDouble("value"));
                        module.addVar(new DefInstruction(new RefOperand(varName), new ValueOperand(varValue)));
                        continue;
                    } catch (JSONException e) {
                        // Ignore
                    }
                    try {
                        varValue = new StringValue(var.getString("value"));
                        module.addVar(new DefInstruction(new RefOperand(varName), new ValueOperand(varValue)));
                        continue;
                    } catch (JSONException e) {
                        // Ignore
                    }
                    try {
                        varValue = new BooleanValue(var.getBoolean("value"));
                        module.addVar(new DefInstruction(new RefOperand(varName), new ValueOperand(varValue)));
                        continue;
                    } catch (JSONException e) {
                        // Ignore
                    }
                }
            }

            String routineName;
            for (int i = 0; i < routines.length(); i++) {
                JSONObject routineJSON = routines.getJSONObject(i);
                routineName = routineJSON.getString("name");
                Routine routine = Routine.loadFromJSON(routineJSON);
                if (routine != null) {
                    module.defineRoutine(routineName, routine);
                }
            }
            return module;
        } catch (JSONException e) {
            return null;
        }
    }

    public boolean hasMain() {
        return this.routines.get("main") != null;
    }

    public Module defineRoutine(String name, Routine routine) throws OverdefinedException {
        if (this.routines.get(name) != null) {
            throw new OverdefinedException();
        }
        this.routines.put(name, routine);
        return this;
    }

    public Routine getRoutine(String name) throws UndefinedException {
        Routine routine = this.routines.get(name);
        if (routine == null) {
            throw new UndefinedException();
        }
        return routine;
    }

    public Module addVar(DefInstruction instruction) {
        this.vars.add(instruction);
        return this;
    }

    public Module addVars(DefInstruction[] instructions) {
        for (DefInstruction instruction : instructions) {
            this.addVar(instruction);
        }
        return this;
    }

    public DefInstruction[] getVars() {
        return this.vars.toArray(new DefInstruction[0]);
    }

    @Override
    public String toString() {
        try {
            String string = new String();
            for (DefInstruction i : getVars()) {
                string += i + "\n";
            }
            for (String key : this.routines.keySet()) {
                Routine routine = this.routines.get(key);
                String instruction = new String();
                for (Instruction i : routine.getInstructions()) {
                    instruction += "\t" + i + "\n";
                }
                string += "\n" + key + " {\n" + instruction + "}\n";
            }
            return string;
        } catch (Exception e) {
            return new String();
        }
    }
}
