package org.lucylang.ljvm.parser;

import org.lucylang.ljvm.LJVMTest;

import static org.lucylang.ljvm.parser.Token.Type.*;

import java.io.IOException;
import java.io.StringReader;

public class LexerTest extends LJVMTest {
    public LexerTest(String testName) {
        super(testName);
    }

    private final void runTest(String input, Token.Type... output) {
        Lexer lexer = new Lexer(new StringReader(input));
        int i = 0;
        Token actual, expected;
        try {
            do {
                assertTrue(i < output.length);
                expected = new Token(output[i++], 0, 0, "");
                try {
                    actual = lexer.nextToken();
                    assertEquals(expected.getType(), actual.getType());
                } catch (Error e) {
                    if (expected != null)
                        fail(e.getMessage());
                    return;
                }
            } while (!actual.isEOF());
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    public void testKWs() {
        runTest("add a a b", ADD, IDENT, IDENT, IDENT);
    }
}
