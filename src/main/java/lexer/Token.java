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

    public boolean isValue() {
        return isVariable() || isScalar();
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

    public boolean isType() {
        return "bool".equals(this.value)
                || "int".equals(this.value)
                || "float".equals(this.value)
                || "void".equals(this.value)
                || "string".equals(this.value);
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

    public static Token makeString(PeekIterator<Character> iterator) throws LexicalException {
        StringBuilder sb = new StringBuilder();
        int state = 0;

        while (iterator.hasNext()) {
            char c = iterator.next();
            switch (state) {
                case 0 -> {
                    if (c == '\'') {
                        state = 1;
                    } else { // c == '"'
                        state = 2;
                    }
                    sb.append(c);
                }
                case 1 -> {
                    if (c == '\'') {
                        return new Token(TokenType.STRING, sb.append(c).toString());
                    }
                    sb.append(c);
                }
                case 2 -> {
                    if (c == '"') {
                        return new Token(TokenType.STRING, sb.append(c).toString());
                    }
                    sb.append(c);
                }
                default -> throw new LexicalException("illegal grammar");
            }
        }
        throw new LexicalException("illegal grammar");
    }

    public static Token makeOp(PeekIterator<Character> iterator) throws LexicalException {
        int state = 0;

        while (iterator.hasNext()) {
            char lookahead = iterator.next();
            switch (state) {
                case 0:
                    switch (lookahead) {
                        case '+':
                            state = 1;
                            break;
                        case '-':
                            state = 2;
                            break;
                        case '*':
                            state = 3;
                            break;
                        case '/':
                            state = 4;
                            break;
                        case '>':
                            state = 5;
                            break;
                        case '<':
                            state = 6;
                            break;
                        case '=':
                            state = 7;
                            break;
                        case '!':
                            state = 8;
                            break;
                        case '&':
                            state = 9;
                            break;
                        case '|':
                            state = 10;
                            break;
                        case '^':
                            state = 11;
                            break;
                        case '%':
                            state = 12;
                            break;
                        case ',':
                            return new Token(TokenType.OPERATOR, ",");
                        case ';':
                            return new Token(TokenType.OPERATOR, ";");
                        default:
                            throw new LexicalException(Constants.UNEXPECTED_ERROR);
                    }
                    break;
                case 1:
                    if (lookahead == '+') {
                        return new Token(TokenType.OPERATOR, "++");
                    } else if (lookahead == '=') {
                        return new Token(TokenType.OPERATOR, "+=");
                    } else {
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, "+");
                    }
                case 2:
                    if (lookahead == '-') {
                        return new Token(TokenType.OPERATOR, "--");
                    } else if (lookahead == '=') {
                        return new Token(TokenType.OPERATOR, "-=");
                    } else {
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, "-");
                    }
                case 3:
                    if (lookahead == '*') {
                        return new Token(TokenType.OPERATOR, "**");
                    } else if (lookahead == '=') {
                        return new Token(TokenType.OPERATOR, "*=");
                    } else {
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, "*");
                    }
                case 4:
                    if (lookahead == '/') {
                        return new Token(TokenType.OPERATOR, "//");
                    } else if (lookahead == '=') {
                        return new Token(TokenType.OPERATOR, "/=");
                    } else {
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, "/");
                    }
                case 5:
                    if (lookahead == '>') {
                        return new Token(TokenType.OPERATOR, ">>");
                    } else if (lookahead == '=') {
                        return new Token(TokenType.OPERATOR, ">=");
                    } else {
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, ">");
                    }
                case 6:
                    if (lookahead == '<') {
                        return new Token(TokenType.OPERATOR, "<<");
                    } else if (lookahead == '=') {
                        return new Token(TokenType.OPERATOR, "<=");
                    } else {
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, "<");
                    }
                case 7:
                    if (lookahead == '=') {
                        return new Token(TokenType.OPERATOR, "==");
                    } else {
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, "=");
                    }
                case 8:
                    if (lookahead == '=') {
                        return new Token(TokenType.OPERATOR, "!=");
                    } else {
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, "!");
                    }
                case 9:
                    if (lookahead == '&') {
                        return new Token(TokenType.OPERATOR, "&&");
                    } else if (lookahead == '=') {
                        return new Token(TokenType.OPERATOR, "&=");
                    } else {
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, "&");
                    }
                case 10:
                    if (lookahead == '|') {
                        return new Token(TokenType.OPERATOR, "||");
                    } else if (lookahead == '=') {
                        return new Token(TokenType.OPERATOR, "|=");
                    } else {
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, "|");
                    }
                case 11:
                    if (lookahead == '^') {
                        return new Token(TokenType.OPERATOR, "^^");
                    } else if (lookahead == '=') {
                        return new Token(TokenType.OPERATOR, "^=");
                    } else {
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, "^");
                    }
                case 12:
                    if (lookahead == '=') {
                        return new Token(TokenType.OPERATOR, "%=");
                    } else {
                        iterator.putBack();
                        return new Token(TokenType.OPERATOR, "%");
                    }
                default:
                    throw new LexicalException(Constants.UNEXPECTED_ERROR);
            }
        }

        throw new LexicalException(Constants.UNEXPECTED_ERROR);
    }

    public static Token makeNumber(PeekIterator<Character> iterator) throws LexicalException {
        StringBuilder sb = new StringBuilder();
        int state = 0;
        while (iterator.hasNext()) {
            char lookahead = iterator.peek();

            switch (state) {
                case 0:
                    if (lookahead == '0') {
                        state = 1;
                    } else if (AlphabetHelper.isNumber(lookahead)) {
                        state = 2;
                    } else if (lookahead == '+' || lookahead == '-') {
                        state = 3;
                    } else if (lookahead == '.') {
                        state = 5;
                    }
                    break;
                case 1:
                    if (lookahead == '0') {
                        // 00000的这些情况
                        state = 1;
                    } else if (AlphabetHelper.isNumber(lookahead)) {
                        state = 2;
                    } else if (lookahead == '.') {
                        state = 4;
                    } else {
                        return new Token(TokenType.INTEGER, sb.toString());
                    }
                    break;
                case 2:
                    if (AlphabetHelper.isNumber(lookahead)) {
                        state = 2;
                    } else if (lookahead == '.') {
                        state = 4;
                    } else {
                        return new Token(TokenType.INTEGER, sb.toString());
                    }
                    break;
                case 3:
                    if (AlphabetHelper.isNumber(lookahead)) {
                        state = 2;
                    } else if (lookahead == '.') {
                        state = 5;
                    } else {
                        throw new LexicalException(lookahead);
                    }
                    break;
                case 4:
                    if (lookahead == '.') {
                        throw new LexicalException(lookahead);
                    } else if (AlphabetHelper.isNumber(lookahead)) {
                        state = 20;
                    } else {
                        return new Token(TokenType.FLOAT, sb.toString());
                    }
                    break;
                case 5:
                    if (AlphabetHelper.isNumber(lookahead)) {
                        state = 20;
                    } else {
                        throw new LexicalException(lookahead);
                    }
                    break;
                case 20:
                    if (AlphabetHelper.isNumber(lookahead)) {
                        state = 20;
                    } else if (lookahead == '.') {
                        throw new LexicalException(lookahead);
                    } else {
                        return new Token(TokenType.FLOAT, sb.toString());
                    }
                    break;
                default:
                    throw new LexicalException(lookahead);
            } // end switch

            iterator.next();
            sb.append(lookahead);
        } // end while

        throw new LexicalException("Grammatical errors");
    }

    @Override
    public String toString() {
        return String.format("type %s, value %s", this.type, this.value);
    }

    public boolean isNumber() {
        return this.type == TokenType.INTEGER || this.type == TokenType.FLOAT;
    }

    public boolean isOperator() {
        return this.type == TokenType.OPERATOR;
    }
}
