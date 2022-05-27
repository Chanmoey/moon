package parser.utils;

import common.PeekIterator;
import lexer.Token;
import lexer.TokenType;

import java.util.stream.Stream;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public class PeekTokenIterator extends PeekIterator<Token> {
    public PeekTokenIterator(Stream<Token> stream) {
        super(stream);
    }

    public Token nextMatch(String value) throws ParseException {
        Token token = this.next();
        if (!token.getValue().equals(value)) {
            throw new ParseException(token);
        }

        return token;
    }

    public Token nextMatch(TokenType type) throws ParseException {
        Token token = this.next();
        if (!token.getType().equals(type)) {
            throw new ParseException(token);
        }

        return token;
    }
}
