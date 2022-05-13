package lexer;

/**
 * @author Chanmoey
 * @date 2022年05月13日
 */
public class LexicalException extends Exception {

    private final String message;

    public LexicalException(char c) {
        this.message = String.format("illegal character %c", c);
    }

    public LexicalException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
