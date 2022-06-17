package parser.ast;

import lexer.Token;
import parser.utils.PeekTokenIterator;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public class Variable extends Factor{

    private Token typeLexeme = null;
    protected Variable(ASTNode parent, PeekTokenIterator iterator) {
        super(parent, iterator);
    }

    public Variable(ASTNode parent, Token token) {
        super(parent, token);
        this.type = ASTNodeTypes.VARIABLE;
    }

    public void setTypeLexeme(Token token) {
        this.typeLexeme = token;
    }
}
