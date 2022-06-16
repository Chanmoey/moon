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

    protected Factor(ASTNode parent, Token token) {
        super(parent);
        this.lexeme = token;
        this.label = token.getValue();
    }

    public static ASTNode parse(ASTNode parent, PeekTokenIterator iterator) {
        var token = iterator.peek();
        var type = token.getType();

        if (type == TokenType.VARIABLE) {
            iterator.next();
            return new Variable(parent, token);
        } else if (token.isScalar()) {
            iterator.next();
            return new Scalar(parent, token);
        }

        return null;
    }
}
