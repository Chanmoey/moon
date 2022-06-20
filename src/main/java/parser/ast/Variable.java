package parser.ast;

import lexer.Token;
import parser.utils.PeekTokenIterator;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public class Variable extends Factor {

    private Token typeLexeme = null;

    public Variable(Token token) {
        super(token);
        this.type = ASTNodeTypes.VARIABLE;
    }

    public void setTypeLexeme(Token token) {
        this.typeLexeme = token;
    }

    public Token getTypeLexeme() {
        return this.typeLexeme;
    }
}
