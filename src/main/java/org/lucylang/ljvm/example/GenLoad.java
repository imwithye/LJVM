package org.lucylang.ljvm.example;

import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.driver.Driver;
import org.lucylang.ljvm.machine.instruction.*;
import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.machine.module.Routine;
import org.lucylang.ljvm.value.BooleanValue;
import org.lucylang.ljvm.value.NumberValue;
import org.lucylang.ljvm.value.StringValue;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class GenLoad {
    public static void main(String[] args) {
        try {
            String path = "D:/fibonacci.lyo";
            System.out.println("Bootstrap fibonacci module and write it to " + path);
            Module module = new Module();
            module.addVars(new DefInstruction[]{
                    new DefInstruction(new RefOperand("result"), new ValueOperand(new NumberValue(0))),
            });
            module.defineRoutine("fibonacci", new Routine(new Instruction[]{
                    new DefInstruction(new RefOperand("n"), new ValueOperand(new NumberValue(0))),
                    new PopInstruction(new RefOperand("n")),
                    new DefInstruction(new RefOperand("cmp"), new ValueOperand(new BooleanValue(false))),
                    new LesInstruction(new RefOperand("cmp"), new RefOperand("n"), new ValueOperand(new NumberValue(3))),
                    new BeqInstruction(new RefOperand("cmp"), new ValueOperand(new NumberValue(19))),
                    // IF n >= 3
                    new DefInstruction(new RefOperand("result"), new RefOperand("n")),
                    new SubInstruction(new RefOperand("result"), new RefOperand("result"), new ValueOperand(new NumberValue(1))),
                    new PushInstruction(new RefOperand("result")),
                    new CallInstruction(new ValueOperand(new StringValue("fibonacci"))),
                    new PopInstruction(new RefOperand("result")),
                    new DefInstruction(new RefOperand("sum"), new RefOperand("result")),

                    new MovInstruction(new RefOperand("result"), new RefOperand("n")),
                    new SubInstruction(new RefOperand("result"), new RefOperand("result"), new ValueOperand(new NumberValue(2))),
                    new PushInstruction(new RefOperand("result")),
                    new CallInstruction(new ValueOperand(new StringValue("fibonacci"))),
                    new PopInstruction(new RefOperand("result")),
                    new AddInstruction(new RefOperand("sum"), new RefOperand("sum"), new RefOperand("result")),

                    //   return result
                    new PushInstruction(new RefOperand("sum")),
                    new RetInstruction(),
                    // ELSE
                    //   return n
                    new PushInstruction(new ValueOperand(new NumberValue(1))),
                    new RetInstruction()
            }));
            module.defineRoutine("main", new Routine(new Instruction[]{
                    new PushInstruction(new ValueOperand(new NumberValue(10))),
                    new CallInstruction(new ValueOperand(new StringValue("fibonacci"))),
                    new PopInstruction(new RefOperand("result")),
                    new DefInstruction(new RefOperand("text"), new ValueOperand(new StringValue("The value of fibonacci 10 is "))),
                    new StrInstruction(new RefOperand("result"), new RefOperand("result")),
                    new AddInstruction(new RefOperand("text"), new RefOperand("text"), new RefOperand("result")),
                    new AddInstruction(new RefOperand("text"), new RefOperand("text"), new ValueOperand(new StringValue("\n"))),
                    new PutInstruction(new RefOperand("text")),
                    new RetInstruction()
            }));
            Driver driver = new Driver();
            driver.generateModule(module, new FileOutputStream(path));
            System.out.println(">>>>");
            System.out.println(module);
            System.out.println("<<<<");
            System.out.println("Done.");
            System.out.println();

            System.out.println("Load fibonacci module from " + path + " and execute:");
            System.out.println();

            Module fibonacci = driver.loadModule(new FileInputStream(path));
            Machine vm = driver.initVM();
            vm.execute(fibonacci);

            System.out.println();
            System.out.println("Done.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
