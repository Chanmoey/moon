package parser;

import parser.ast.*;
import parser.utils.ParseException;
import parser.utils.PeekTokenIterator;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public class SimpleParser {

    private SimpleParser() {
        throw new IllegalStateException("Don't call this method!");
    }

    // Expr -> digit + Expr
    public static ASTNode parse(PeekTokenIterator it) throws ParseException {

        var expr = new Expr();
        var scalar = Factor.parse(it);
        // base condition
        if (!it.hasNext()) {
            return scalar;
        }

        expr.setLexeme(it.peek());
        it.nextMatch("+");
        expr.setLabel("+");
        expr.addChild(scalar);
        expr.setType(ASTNodeTypes.BINARY_EXPR);
        var rightNode = parse(it);
        expr.addChild(rightNode);
        return expr;
    }
}
