package parser.ast;

import lexer.Token;
import parser.utils.PeekTokenIterator;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public class Scalar extends Factor{
    public Scalar(Token token) {
        super(token);
        this.type = ASTNodeTypes.SCALAR;
    }
}
