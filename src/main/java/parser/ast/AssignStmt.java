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

    public static ASTNode parse(PeekTokenIterator it) throws ParseException {
        var stmt = new AssignStmt();
        var tkn = it.peek();
        var factor = Factor.parse(it);
        if (factor == null) {
            throw new ParseException(tkn);
        }
        stmt.addChild(factor);
        var lexeme = it.nextMatch("=");
        var expr = Expr.parse(it);
        stmt.addChild(expr);
        stmt.setLexeme(lexeme);
        return stmt;

    }
}
