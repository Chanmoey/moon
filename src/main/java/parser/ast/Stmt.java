package parser.ast;

import parser.utils.ParseException;
import parser.utils.PeekTokenIterator;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public abstract class Stmt extends ASTNode {

    protected Stmt(ASTNodeTypes types, String label) {
        super(types, label);
    }

    protected Stmt(ASTNode parent, ASTNodeTypes type, String label) {
        super(parent, type, label);
    }

    public static ASTNode parseStmt(ASTNode parent, PeekTokenIterator iterator) throws ParseException {

        if (!iterator.hasNext()) {
            return null;
        }

        var token = iterator.next();
        var lookahead = iterator.peek();
        iterator.pushBack();

        if (token.isVariable() && lookahead != null && "=".equals(lookahead.getValue())) {
            return AssignStmt.parse(iterator);
        } else if ("var".equals(token.getValue())) {
            return DeclareStmt.parse(parent, iterator);
        }

        return null;
    }
}
