package org.lucylang.ljvm.parser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.lucylang.ljvm.parser.Token.Type.*;

import java.io.StringReader;
import java.util.ArrayList;

public class LexerTest extends TestCase {
    public LexerTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(LexerTest.class);
    }

    private ArrayList<Token> getToken(String input) throws Exception {
        Lexer lexer = new Lexer(new StringReader(input));
        ArrayList<Token> result = new ArrayList<Token>();
        Token actual;
        do {
            actual = lexer.nextToken();
            result.add(actual);
        } while (!actual.isEOF());
        return result;
    }

    private void runTest(String input, Token.Type... output) {
        Lexer lexer = new Lexer(new StringReader(input));
        int i = 0;
        Token actual;
        Token.Type expected;
        try {
            do {
                assertTrue(i < output.length);
                expected = output[i++];
                try {
                    actual = lexer.nextToken();
                    assertEquals(expected, actual.getType());
                } catch (ParserException e) {
                    if (expected != null)
                        fail(e.getMessage());
                    return;
                }
            } while (!actual.isEOF());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    public void testKWs() {
        runTest("func", FUNC, EOF);
        runTest("func test()", FUNC, ID, LPAREN, RPAREN, EOF);
    }

    public void testString() throws Exception {
        runTest("\"this is a string\"", STRING_LITERAL, EOF);
        runTest("\"this is\\\" a string\"", STRING_LITERAL, EOF);
        runTest("\"this is\\n a string\"", STRING_LITERAL, EOF);
        runTest("\"this is\\r a string\"", STRING_LITERAL, EOF);

        ArrayList<Token> tokens;
        tokens = getToken("\"this is a string\"");
        assertEquals("this is a string", tokens.get(0).getLexeme());
        tokens = getToken("\"this is\\\" a string\"");
        assertEquals("this is\" a string", tokens.get(0).getLexeme());
        tokens = getToken("\"this is\\n a string\"");
        assertEquals("this is\n a string", tokens.get(0).getLexeme());
        tokens = getToken("\"this is\\r a string\"");
        assertEquals("this is\r a string", tokens.get(0).getLexeme());
    }
}
