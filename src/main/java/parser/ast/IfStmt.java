package parser.ast;

import parser.utils.ParseException;
import parser.utils.PeekTokenIterator;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public class IfStmt extends Stmt {
    public IfStmt(ASTNode parent) {
        super(parent, ASTNodeTypes.IF_STMT, "if");
    }

    public static ASTNode parse(ASTNode parent, PeekTokenIterator iterator) throws ParseException {
        return parseIf(parent, iterator);
    }

    public static ASTNode parseIf(ASTNode parent, PeekTokenIterator iterator) throws ParseException {
        var lexeme = iterator.nextMatch("if");
        iterator.nextMatch("(");
        var ifStmt = new IfStmt(parent);
        ifStmt.setLexeme(lexeme);
        var expr = Expr.parse(parent, iterator);
        ifStmt.addChild(expr);
        iterator.nextMatch(")");

        var block = Block.parse(parent, iterator);
        ifStmt.addChild(block);

        var tail = parseTail(parent, iterator);

        if (tail != null) {
            ifStmt.addChild(tail);
        }

        return ifStmt;
    }

    private static ASTNode parseTail(ASTNode parent, PeekTokenIterator iterator) throws ParseException {

        if (!iterator.hasNext() || !"else".equals(iterator.peek().getValue())) {
            return null;
        }
        iterator.nextMatch("else");

        var lookahead = iterator.peek();

        if ("{".equals(lookahead.getValue())) {
            return Block.parse(parent, iterator);
        } else if ("if".equals(lookahead.getValue())) {
            return parseIf(parent, iterator);
        } else {
            return null;
        }
    }
}