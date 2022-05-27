package parser.ast;

import parser.utils.PeekTokenIterator;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public class Scalar extends Factor{
    public Scalar(ASTNode parent, PeekTokenIterator iterator) {
        super(parent, iterator);
    }
}
