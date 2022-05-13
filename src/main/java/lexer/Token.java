package lexer;

/**
 * @author Chanmoey
 * @date 2022年05月11日
 */
public class Token {

    private TokenType type;
    private String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return this.type;
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

    @Override
    public String toString() {
        return String.format("type %s, value %s", this.type, this.value);
    }
}
