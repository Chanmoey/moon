package parser.ast;

import parser.utils.ParseException;
import parser.utils.PeekTokenIterator;

/**
 * @author Chanmoey
 * @date 2022年06月17日
 */
public class AssignStmt extends Stmt {

    public AssignStmt() {
        super(ASTNodeTypes.ASSIGN_STMT, "assign");
    }

    public static ASTNode parse(PeekTokenIterator iterator) throws ParseException {
        var stmt = new AssignStmt();
        var tkn = iterator.peek();
        var factor = Factor.parse(null, iterator);
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
