package parser;

import parser.ast.ASTNode;
import parser.ast.ASTNodeTypes;
import parser.ast.Expr;
import parser.ast.Scalar;
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
    public static ASTNode parse(PeekTokenIterator iterator) throws ParseException {

        Expr expr = new Expr(null);
        Scalar scalar = new Scalar(expr, iterator);
        // 递归终止
        if (!iterator.hasNext()) {
            return scalar;
        }

        expr.setLexeme(iterator.peek());
        iterator.nextMatch("+");
        expr.setLabel("+");
        // 添加左节点
        expr.addChild(scalar);
        expr.setType(ASTNodeTypes.BINARY_EXPR);
        ASTNode rightNode = parse(iterator);
        expr.addChild(rightNode);
        return expr;
    }
}
