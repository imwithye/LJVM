package org.lucylang.ljvm.driver;

import javafx.scene.shape.Path;
import org.apache.commons.cli.*;
import org.apache.commons.cli.Option;
import org.apache.logging.log4j.core.appender.SyslogAppender;
import org.lucylang.ljvm.generator.ModuleCodeGenerator;
import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.driver.generator.Generator;
import org.lucylang.ljvm.driver.loader.Loader;
import org.lucylang.ljvm.machine.module.Linker;
import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.parser.*;
import org.lucylang.ljvm.parser.Parser;
import org.lucylang.ljvm.value.Value;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class Driver {
    private Loader loader;
    private Generator generator;
    private Parser parser;
    private ModuleCodeGenerator codeGenerator;
    private Linker linker;
    Options options;
    private static final String version = "0.1.1";

    public Driver() {
        this.loader = new Loader();
        this.generator = new Generator();
        this.parser = new Parser();
        this.codeGenerator = new ModuleCodeGenerator();
        this.linker = new Linker();
        this.options = new Options();
        this.initOptions();
    }

    private void addOption(Option o) {
        this.options.addOption(o);
    }

    private void initOptions() {
        this.addOption(new Option("h", "help", false, "print the help message and exit"));
        this.addOption(new Option("v", "version", false, "print the version information and exit"));

        Option compile = new Option("c", "compile", true, "compile lucy source code to lucy X bit code");
        compile.setArgName("file");
        compile.setArgs(Option.UNLIMITED_VALUES);
        this.addOption(compile);

        Option output = new Option("o", "output", true, "output file path");
        output.setArgName("output");
        this.addOption(output);

        Option dump = new Option("d", "dump", true, "dump lucy X object to human readable form");
        dump.setArgName("file");
        dump.setArgs(Option.UNLIMITED_VALUES);
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
                String[] in = com.getOptionValues("compile");
                String out = com.getOptionValue("output");
                compile(in, out);
                System.exit(0);
            }
            if (com.hasOption("dump")) {
                String[] in = com.getOptionValues("dump");
                dumpLy(in);
                System.exit(0);
            }

            final String[] remainingArguments = com.getArgs();
            if(remainingArguments.length == 0) {
                this.printHelp();
                System.exit(1);
            }
            runLyx(remainingArguments);
        } catch (ParseException e) {
            this.printHelp();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.print(e.getMessage());
        }
    }

    private String[] loadFiles(String[] src) {
        ArrayList<String> files = new ArrayList<String>();
        for (int i = 0; i < src.length; i++) {
            File file = new File(src[i]);
            loadFiles(file, files);
        }
        return files.toArray(new String[0]);
    }

    private void loadFiles(File path, ArrayList<String> files) {
        if (path.isFile()) {
            files.add(path.getAbsolutePath());
        } else {
            for (File dirOrFile : path.listFiles()) {
                loadFiles(dirOrFile, files);
            }
        }
    }

    public ArrayList<Module> loadModules(String[] src) throws Exception {
        src = loadFiles(src);
        ArrayList<Module> modules = new ArrayList<Module>();
        for (int i = 0; i < src.length; i++) {
            try {
                modules.add(this.loader.load(new FileInputStream(src[i])));
            } catch (ClassNotFoundException e) {
                Reader r = new InputStreamReader(new FileInputStream(src[i]), "UTF8");
                modules.add(codeGenerator.visitModule(this.parser.parseModule(new Lexer(r))));
            }
        }
        return modules;
    }

    public Module loadModule(String[] src) throws Exception {
        Module m = this.linker.linkModules(loadModules(src));
        return m;
    }

    public void compile(String[] src, String output) throws Exception {
        Module module = this.loadModule(src);
        if(output == null) {
            output = module.getName() + ".lyo";
        }
        this.generateModule(module, new FileOutputStream(output));
    }

    public void dumpLy(String[] src) throws Exception {
        Module module = this.loadModule(src);
        System.out.println(module);
    }

    public Value runLyx(String[] src) throws Exception {
        Module module = this.loadModule(src);
        return this.initVM().execute(module);
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
        formatter.printHelp("lucy [options] target", this.options);
    }

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.parse(args);
    }
}
