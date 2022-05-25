package lexer;

import common.PeekIterator;
import lexer.LexicalException;
import lexer.Token;
import lexer.TokenType;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.IconUIResource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Chanmoey
 * @date 2022年05月13日
 */
class TokenTest {

    private void assertToken(Token token, String value, TokenType type) {
        assertEquals(type, token.getType());
        assertEquals(value, token.getValue());
    }

    @Test
    void test_varOrKeyword() {
        var iterator1 = new PeekIterator<>("if abc".chars().mapToObj(x -> (char) x));
        var iterator2 = new PeekIterator<>("true abc".chars().mapToObj(x -> (char) x));

        var token1 = Token.makeVarOrKeyword(iterator1);
        var token2 = Token.makeVarOrKeyword(iterator2);

        this.assertToken(token1, "if", TokenType.KEYWORD);
        this.assertToken(token2, "true", TokenType.BOOLEAN);

        iterator1.next();
        var token3 = Token.makeVarOrKeyword(iterator1);
        this.assertToken(token3, "abc", TokenType.VARIABLE);

        iterator2.next();
        var token4 = Token.makeVarOrKeyword(iterator2);
        this.assertToken(token4, "abc", TokenType.VARIABLE);
    }

    @Test
    void test_makeString() throws LexicalException {
        String[] tests = {
                "\"abc\"",
                "'abc'"
        };

        for (String test : tests) {
            var it = new PeekIterator<>(test.chars().mapToObj(c -> (char) c));
            var token = Token.makeString(it);
            assertToken(token, test, TokenType.STRING);
        }
    }

    @Test
    void test_makeOp() throws LexicalException {
        String[] tests = {
                "+ 123",
                "++adf",
                "/=x",
                "==1",
                "&989",
                "&=asf",
                "||xx",
                "^=111",
                "%7"
        };

        String[] results = {"+", "++", "/=", "==", "&", "&=", "||", "^=", "%"};
        for (int i = 0; i < tests.length; i++) {
            String test = tests[i];
            var it = new PeekIterator<>(test.chars().mapToObj(c -> (char) c));
            var token = Token.makeOp(it);
            assertToken(token, results[i], TokenType.OPERATOR);
        }
    }

    @Test
    void test_makeNumber() throws LexicalException {
        String[] tests = {
                "+0 qa",
                "-0 adf",
                ".5 adf",
                ".1543 adf",
                "341.1514351 adf",
                "-1000.1234*53123",
                "0000*111"
        };

        for (String test : tests) {
            var it = new PeekIterator<Character>(test.chars().mapToObj(c -> (char) c));
            var token = Token.makeNumber(it);
            var splitValue = test.split("[* ]+");
            System.out.print(splitValue[0] + " == ");
            System.out.println(token.getValue());
            assertToken(token, splitValue[0], (test.indexOf('.') != -1 ? TokenType.FLOAT : TokenType.INTEGER));
        }
    }
}
