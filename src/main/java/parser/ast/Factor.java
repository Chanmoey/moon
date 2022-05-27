package parser.ast;

import lexer.Token;
import lexer.TokenType;
import parser.utils.PeekTokenIterator;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public abstract class Factor extends ASTNode{
    protected Factor(ASTNode parent, PeekTokenIterator iterator) {
        super(parent);

        Token token = iterator.next();
        TokenType tokenType = token.getType();

        if (tokenType == TokenType.VARIABLE) {
            this.type = ASTNodeTypes.VARIABLE;
        } else {
            this.type = ASTNodeTypes.SCALAR;
        }

        this.label = token.getValue();
        this.lexeme = token;
    }
}
