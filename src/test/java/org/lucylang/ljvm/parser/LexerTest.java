package org.lucylang.ljvm.parser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.lucylang.ljvm.parser.Parser.Terminals;

import java.io.StringReader;
import java.util.ArrayList;

public class LexerTest extends TestCase {
    public LexerTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(LexerTest.class);
    }

    private ArrayList<beaver.Symbol> getToken(String input) throws Exception {
        Lexer lexer = new Lexer(new StringReader(input));
        ArrayList<beaver.Symbol> result = new ArrayList<beaver.Symbol>();
        beaver.Symbol actual;
        do {
            actual = lexer.nextToken();
            result.add(actual);
        } while (actual.getId() != Parser.Terminals.EOF);
        return result;
    }

    private void runTest(String input, short... output) {
        Lexer lexer = new Lexer(new StringReader(input));
        int i = 0;
        beaver.Symbol actual;
        short expected;
        try {
            do {
                assertTrue(i < output.length);
                expected = output[i++];
                try {
                    actual = lexer.nextToken();
                    assertEquals(expected, actual.getId());
                } catch (beaver.Scanner.Exception e) {
                        fail(e.getMessage());
                    return;
                }
            } while (actual.getId() != Parser.Terminals.EOF);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    public void testKWs() {
        runTest("func", Terminals.FUNC, Terminals.EOF);
        runTest("func test()", Terminals.FUNC, Terminals.ID, Terminals.LPAREN, Terminals.RPAREN, Terminals.EOF);
    }

    public void testString() throws Exception {
        runTest("\"this is a string\"", Terminals.STRING_LITERAL, Terminals.EOF);
        runTest("\"this is\\\" a string\"", Terminals.STRING_LITERAL, Terminals.EOF);
        runTest("\"this is\\n a string\"", Terminals.STRING_LITERAL, Terminals.EOF);
        runTest("\"this is\\r a string\"", Terminals.STRING_LITERAL, Terminals.EOF);

        ArrayList<beaver.Symbol> tokens;
        tokens = getToken("\"this is a string\"");
        assertEquals("this is a string", tokens.get(0).value);
        tokens = getToken("\"this is\\\" a string\"");
        assertEquals("this is\" a string", tokens.get(0).value);
        tokens = getToken("\"this is\\n a string\"");
        assertEquals("this is\n a string", tokens.get(0).value);
        tokens = getToken("\"this is\\r a string\"");
        assertEquals("this is\r a string", tokens.get(0).value);
    }
}
