package org.lucylang.ljvm.parser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.StringReader;

public class ParserTest extends TestCase {
    public ParserTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(ParserTest.class);
    }

    private void runTest(String src, boolean succeed) {
        Parser parser = new Parser();
        try {
            parser.parse(new Lexer(new StringReader(src)));
            if (!succeed) {
                fail("Test was supposed to fail, but succeeded");
            }
        } catch (beaver.Parser.Exception e) {
            if (succeed) {
                fail(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    private void runTest(String src) {
        runTest(src, true);
    }

    public void testGrammar() {
        runTest("func main(){\n" +
                "   a = 10\n" +
                "}");
        runTest("func main(){\n" +
                "   var a\n" +
                "}");
        runTest("func main(){\n" +
                "   var a = 10\n" +
                "}");
        runTest("func main(){\n" +
                "   var a = 10, b = 20, c = \"Hello World\"\n" +
                "}");
    }
}
