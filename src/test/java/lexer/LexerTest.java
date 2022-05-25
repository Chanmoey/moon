package lexer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Chanmoey
 * @date 2022年05月25日
 */
class LexerTest {

    private void assertToken(Token token, String value, TokenType type) {
        assertEquals(type, token.getType());
        assertEquals(value, token.getValue());
    }

    @Test
    void test_expression() throws LexicalException {
        var lexer = new Lexer();

        var source = "(a + b) ^ 100.10 == +100 -20";
        var tokenList = lexer.analyse(source.chars().mapToObj(x -> (char) x));
        assertEquals(11, tokenList.size());

        assertToken(tokenList.get(0), "(", TokenType.BRACKET);
        assertToken(tokenList.get(1), "a", TokenType.VARIABLE);
        assertToken(tokenList.get(2), "+", TokenType.OPERATOR);
        assertToken(tokenList.get(3), "b", TokenType.VARIABLE);
        assertToken(tokenList.get(4), ")", TokenType.BRACKET);
        assertToken(tokenList.get(5), "^", TokenType.OPERATOR);
        assertToken(tokenList.get(6), "100.10", TokenType.FLOAT);
        assertToken(tokenList.get(7), "==", TokenType.OPERATOR);
        assertToken(tokenList.get(8), "+100", TokenType.INTEGER);
        assertToken(tokenList.get(9), "-", TokenType.OPERATOR);
        assertToken(tokenList.get(10), "20", TokenType.INTEGER);
    }

    @Test
    void test_function() throws LexicalException {
        var source = "def sum(a, b) {" +
                "print(a + b)" +
                "}" +
                "sum(100, 200)";
        var lexer = new Lexer();

        var tokenList = lexer.analyse(source.chars().mapToObj(x -> (char) x));

        assertToken(tokenList.get(0), "def", TokenType.KEYWORD);
        assertToken(tokenList.get(1), "sum", TokenType.VARIABLE);
        assertToken(tokenList.get(2), "(", TokenType.BRACKET);
        assertToken(tokenList.get(3), "a", TokenType.VARIABLE);
        assertToken(tokenList.get(4), ",", TokenType.OPERATOR);
        assertToken(tokenList.get(5), "b", TokenType.VARIABLE);
        assertToken(tokenList.get(6), ")", TokenType.BRACKET);
        assertToken(tokenList.get(7), "{", TokenType.BRACKET);
        assertToken(tokenList.get(8), "print", TokenType.VARIABLE);
        assertToken(tokenList.get(9), "(", TokenType.BRACKET);
        assertToken(tokenList.get(10), "a", TokenType.VARIABLE);
        assertToken(tokenList.get(11), "+", TokenType.OPERATOR);
        assertToken(tokenList.get(12), "b", TokenType.VARIABLE);
        assertToken(tokenList.get(13), ")", TokenType.BRACKET);
        assertToken(tokenList.get(14), "}", TokenType.BRACKET);
        assertToken(tokenList.get(15), "sum", TokenType.VARIABLE);
        assertToken(tokenList.get(16), "(", TokenType.BRACKET);
        assertToken(tokenList.get(17), "100", TokenType.INTEGER);
        assertToken(tokenList.get(18), ",", TokenType.OPERATOR);
        assertToken(tokenList.get(19), "200", TokenType.INTEGER);
        assertToken(tokenList.get(20), ")", TokenType.BRACKET);
    }

    @Test
    void test_comment() throws LexicalException {
        var source = "/*asjdlfjalsdfja\najsdlfjao*/a = 1";
        var lexer = new Lexer();
        var tokenList = lexer.analyse(source.chars().mapToObj(c -> (char)c));
        assertEquals(3, tokenList.size());
    }
}
