package org.lucylang.ljvm.driver;

import org.apache.commons.cli.*;
import org.apache.commons.cli.Option;
import org.lucylang.ljvm.exception.RuntimeException;
import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.driver.generator.Generator;
import org.lucylang.ljvm.driver.loader.Loader;
import org.lucylang.ljvm.machine.module.Module;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Driver {
    private Loader loader;
    private Generator generator;
    Options options;
    private static final String version = "0.1.0";

    public Driver() {
        this.loader = new Loader();
        this.generator = new Generator();
        this.options = new Options();
        this.initOptions();
    }

    private void addOption(Option o) {
        this.options.addOption(o);
    }

    private void initOptions() {
        this.addOption(new Option("h", "help", false, "print the help message and exit"));
        this.addOption(new Option("d", "dump", false, "dump module object to human readable form"));
        this.addOption(new Option("v", "version", false, "print the version information and exit"));
    }

    public void parse(String[] args) {
        try {
            CommandLine com = new DefaultParser().parse(options, args);

            if (com.hasOption("help")) {
                this.printHelp();
                System.exit(0);
            }
            if (com.hasOption("version")) {
                System.out.println(Driver.version);
                System.exit(0);
            }

            final String[] remainingArguments = com.getArgs();
            if (remainingArguments.length != 1) {
                this.printHelp();
                System.exit(1);
            }
            String file = remainingArguments[0];
            Module module = this.loadModule(new FileInputStream(file));

            if (com.hasOption("dump")) {
                System.out.print(module);
                System.exit(0);
            } else {
                this.initVM().execute(module);
            }
        } catch (ParseException e) {
            this.printHelp();
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.print("Error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Runtime Error: " + e.getMessage());
        }
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

    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("ljvm [options] target", this.options);
    }

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.parse(args);
    }
}
