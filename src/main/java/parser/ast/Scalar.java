package parser.ast;

import lexer.Token;
import parser.utils.PeekTokenIterator;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public class Scalar extends Factor{
    public Scalar(ASTNode parent, PeekTokenIterator iterator) {
        super(parent, iterator);
    }

    public Scalar(ASTNode parent, Token token) {
        super(parent, token);
    }
}
