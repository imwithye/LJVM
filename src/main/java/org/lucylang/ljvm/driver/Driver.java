package org.lucylang.ljvm.driver;

import org.apache.commons.cli.*;
import org.apache.commons.cli.Option;
import org.lucylang.ljvm.generator.ModuleCodeGenerator;
import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.driver.generator.Generator;
import org.lucylang.ljvm.driver.loader.Loader;
import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.parser.*;
import org.lucylang.ljvm.parser.Parser;
import org.lucylang.ljvm.value.Value;

import java.io.*;

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
        this.addOption(new Option("v", "version", false, "print the version information and exit"));
        Option compile = new Option("c", "compile", true, "compile lucy source code to lucy bit code");
        compile.setArgName("file");
        this.addOption(compile);
        Option output = new Option("o", "output", true, "output file path");
        output.setArgName("output");
        this.addOption(output);
        Option run = new Option("r", "run", true, "run lucy X bit code");
        run.setArgName("file");
        this.addOption(run);
        Option dump = new Option("d", "dump", true, "dump module object to human readable form");
        dump.setArgName("file");
        this.addOption(dump);
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
            if (com.hasOption("compile")) {
                String in = com.getOptionValue("compile");
                String out = com.getOptionValue("output");
                if (out == null) {
                    out = "a.lyo";
                }
                compile(in, out);
                System.exit(0);
            }
            if (com.hasOption("run")) {
                String in = com.getOptionValue("run");
                runLyx(in);
                System.exit(0);
            }
            if (com.hasOption("dump")) {
                String in = com.getOptionValue("dump");
                dumpLy(in);
                System.exit(0);
            }

            final String[] remainingArguments = com.getArgs();
            if (remainingArguments.length != 1) {
                this.printHelp();
                System.exit(1);
            }
            runLy(remainingArguments[0]);
        } catch (ParseException e) {
            this.printHelp();
            System.exit(1);
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    }

    public void compile(String src, String output) throws Exception {
        Reader r = new InputStreamReader(new FileInputStream(src), "UTF8");
        org.lucylang.ljvm.parser.Parser parser = new Parser();
        org.lucylang.ljvm.node.Module module = parser.parseModule(new Lexer(r));
        ModuleCodeGenerator codeGenerator = new ModuleCodeGenerator();
        this.generateModule(codeGenerator.visitModule(module), new FileOutputStream(output));
    }

    public void dumpLy(String src) throws Exception {
        Reader r = new InputStreamReader(new FileInputStream(src), "UTF8");
        org.lucylang.ljvm.parser.Parser parser = new Parser();
        org.lucylang.ljvm.node.Module module = parser.parseModule(new Lexer(r));
        ModuleCodeGenerator codeGenerator = new ModuleCodeGenerator();
        Module m = codeGenerator.visitModule(module);
        System.out.println(m);
    }

    public Value runLyx(String src) throws Exception {
        Module module = this.loadModule(new FileInputStream(src));
        return this.initVM().execute(module);
    }

    public Value runLy(String src) throws Exception {
        Reader r = new InputStreamReader(new FileInputStream(src), "UTF8");
        org.lucylang.ljvm.parser.Parser parser = new Parser();
        org.lucylang.ljvm.node.Module module = parser.parseModule(new Lexer(r));
        ModuleCodeGenerator codeGenerator = new ModuleCodeGenerator();
        Module m = codeGenerator.visitModule(module);
        Value result = this.initVM().execute(m);
        // TODO: remove in the future
        System.out.print(result.intValue());
        return result;
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
