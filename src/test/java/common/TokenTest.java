package common;

import lexer.Token;
import lexer.TokenType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Chanmoey
 * @date 2022年05月13日
 */
public class TokenTest {

    private void assertToken(Token token, String value, TokenType type) {
        assertEquals(type, token.getType());
        assertEquals(value, token.getValue());
    }

    @Test
    public void test_varOrKeyword() {
        var iterator1 = new PeekIterator<>("if abc".chars().mapToObj(x -> (char)x));
        var iterator2 = new PeekIterator<>("true abc".chars().mapToObj(x -> (char)x));

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
}
