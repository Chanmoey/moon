package lexer;

import common.AlphabetHelper;
import common.Constants;
import common.PeekIterator;

/**
 * @author Chanmoey
 * @date 2022年05月11日
 */
public class Token {

    private final TokenType type;
    private final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public boolean isVariable() {
        return this.type == TokenType.VARIABLE;
    }

    public boolean isScalar() {
        return this.type == TokenType.INTEGER
                || this.type == TokenType.FLOAT
                || this.type == TokenType.STRING
                || this.type == TokenType.BOOLEAN;
    }

    public static Token makeVarOrKeyword(PeekIterator<Character> iterator) {
        StringBuilder sb = new StringBuilder();

        while (iterator.hasNext()) {
            Character ahead = iterator.peek();
            if (AlphabetHelper.isLiteral(ahead)) {
                sb.append(ahead);
            } else {
                break;
            }
            iterator.next();
        }

        String s = sb.toString();
        if (KeyWords.isKeyword(s)) {
            return new Token(TokenType.KEYWORD, s);
        }

        if (Constants.TRUE.equals(s) || Constants.FALSE.equals(s)) {
            return new Token(TokenType.BOOLEAN, s);
        }

        return new Token(TokenType.VARIABLE, s);
    }

    @Override
    public String toString() {
        return String.format("type %s, value %s", this.type, this.value);
    }
}
