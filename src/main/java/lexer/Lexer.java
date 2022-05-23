package lexer;

import common.AlphabetHelper;
import common.PeekIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Chanmoey
 * @date 2022年05月11日
 */
public class Lexer {

    public List<Token> analyse(Stream<?> source) throws LexicalException {
        List<Token> tokenList = new ArrayList<>();
        PeekIterator<Character> iterator = new PeekIterator<>((Stream<Character>) source, (char) 0);

        while (iterator.hasNext()) {
            char c = iterator.next();

            if (c == 0) {
                break;
            }

            char lookahead = iterator.peek();

            if (c == ' ' || c == '\n') {
                continue;
            }

            if (c == '{' || c == '}' || c == '(' || c == ')') {
                tokenList.add(new Token(TokenType.BRACKET, String.valueOf(c)));
                continue;
            }

            if (c == '"' || c == '\'') {
                iterator.pushBack();
                tokenList.add(Token.makeString(iterator));
                continue;
            }

            if (AlphabetHelper.isLetter(c)) {
                iterator.pushBack();
                tokenList.add(Token.makeVarOrKeyword(iterator));
                continue;
            }

            if (AlphabetHelper.isNumber(c)) {
                iterator.pushBack();
                tokenList.add(Token.makeNumber(iterator));
                continue;
            }

            // + -: 3 + 5, +5, 3 * -5
            if ((c == '+' || c == '-' || c == '.') && AlphabetHelper.isNumber(lookahead)) {
                Token lastToken = tokenList.isEmpty() ? null : tokenList.get(tokenList.size() - 1);

                if (lastToken == null || lastToken.isNumber() || lastToken.isOperator()) {
                    iterator.pushBack();
                    tokenList.add(Token.makeNumber(iterator));
                    continue;
                }
            }

            if (AlphabetHelper.isOperator(c)) {
                iterator.pushBack();
                tokenList.add(Token.makeOp(iterator));
                continue;
            }

            throw new LexicalException(c);
        } // end while

        return tokenList;
    }
}
