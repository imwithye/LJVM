package org.lucylang.ljvm.parser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.lucylang.ljvm.parser.Token.Type.*;

import java.io.StringReader;

public class LexerTest extends TestCase {
    public LexerTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(LexerTest.class);
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

        // String Literal Test
        runTest("\"this is a string\"", STRING_LITERAL, EOF);
        runTest("\"this is\\\" a string\"", STRING_LITERAL, EOF);
        runTest("\"this is\\n a string\"", STRING_LITERAL, EOF);
        runTest("\"this is\\r a string\"", STRING_LITERAL, EOF);
    }
}
