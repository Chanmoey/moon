package parser.ast;

import parser.utils.ParseException;
import parser.utils.PeekTokenIterator;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public class DeclareStmt extends Stmt {
    public DeclareStmt(ASTNode parent) {
        super(parent, ASTNodeTypes.DECLARE_STMT, "declare");
    }

    public static ASTNode parse(ASTNode parent, PeekTokenIterator iterator) throws ParseException {
        var stmt = new DeclareStmt(parent);
        iterator.nextMatch("moon");
        var tkn = iterator.peek();
        var factor = Factor.parse(parent, iterator);
        if (factor == null) {
            throw new ParseException(tkn);
        }

        stmt.addChild(factor);
        var lexeme = iterator.nextMatch("=");
        var expr = Expr.parse(iterator);
        stmt.addChild(expr);
        stmt.setLexeme(lexeme);
        return stmt;
    }
}
