package parser.utils;

import lexer.Token;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public class ParseException extends Exception{

    private final String message;

    public ParseException(String message) {
        this.message = message;
    }

    public ParseException(Token token) {
        this.message = String.format("Syntax Error, Unexpected token %s", token.getValue());
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
