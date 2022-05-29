package parser.ast;

import lexer.Token;
import parser.utils.ExprHOF;
import parser.utils.ParseException;
import parser.utils.PeekTokenIterator;
import parser.utils.PriorityTable;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public class Expr extends ASTNode {

    private static final PriorityTable table = new PriorityTable();

    public Expr(ASTNode parent) {
        super(parent);
    }

    public Expr(ASTNode parent, ASTNodeTypes type, Token lexeme) {
        super(parent);
        this.type = type;
        this.label = lexeme.getValue();
        this.lexeme = lexeme;
    }

    /**
     * left: E(k) -> E(k) op(k) E(k+1) | E(k+1)
     * right:
     * E(k) -> E(k+1) E_(k)
     * var e = new Expr(); e.left = E(k+1); e.op = op(k); e.right = E(k+1) E_(k)
     * E_(k) -> op(k) E(k+1) E_(k) | e
     */
    public static ASTNode E(ASTNode parent, int k, PeekTokenIterator iterator) throws ParseException {
        if (k < table.size() - 1) {
            return combine(parent, iterator, () -> E(parent, k + 1, iterator), () -> E_(parent, k, iterator));
        } else {
            return race(
                    iterator,
                    () -> combine(parent, iterator, () -> U(parent, iterator), () -> E_(parent, k, iterator)),
                    () -> combine(parent, iterator, () -> F(parent, iterator), () -> E_(parent, k, iterator))
            );
        }
    }

    public static ASTNode E_(ASTNode parent, int k, PeekTokenIterator iterator) throws ParseException {
        Token token = iterator.peek();
        String value = token.getValue();

        if (table.get(k).contains(value)) {
            Expr expr = new Expr(parent, ASTNodeTypes.BINARY_EXPR, iterator.nextMatch(value));
            expr.addChild(combine(parent, iterator,
                    () -> E(parent, k + 1, iterator),
                    () -> E_(parent, k, iterator)));
        }

        return null;
    }

    private static ASTNode U(ASTNode parent, PeekTokenIterator iterator) throws ParseException {
        Token token = iterator.peek();
        String value = token.getValue();
        ASTNode expr = null;

        if ("(".equals(value)) {
            iterator.nextMatch("(");
            expr = E(parent, 0, iterator);
            iterator.nextMatch(")");
            return expr;
        } else if ("++".equals(value) || "--".equals(value) || "!".equals(value)) {
            Token t = iterator.peek();
            iterator.nextMatch(value);
            Expr unaryExpr = new Expr(parent, ASTNodeTypes.UNARY_EXPR, t);
            unaryExpr.addChild(E(unaryExpr, 0, iterator));
            return unaryExpr;
        }

        return null;
    }

    private static ASTNode F(ASTNode parent, PeekTokenIterator iterator) {
        Token token = iterator.peek();
        if (token.isVariable()) {
            return new Variable(parent, iterator);
        } else {
            return new Scalar(parent, iterator);
        }
    }

    private static ASTNode combine(ASTNode parent, PeekTokenIterator iterator,
                                   ExprHOF aFunc, ExprHOF bFunc) throws ParseException {
        ASTNode a = aFunc.hoc();
        if (a == null) {
            return iterator.hasNext() ? bFunc.hoc() : null;
        }

        ASTNode b = iterator.hasNext() ? bFunc.hoc() : null;
        if (b == null) {
            return a;
        }

        Expr expr = new Expr(parent, ASTNodeTypes.BINARY_EXPR, b.lexeme);
        expr.addChild(a);
        expr.addChild(b.getChild(0));
        return expr;
    }

    private static ASTNode race(PeekTokenIterator iterator, ExprHOF aFunc, ExprHOF bFunc) throws ParseException {
        if (!iterator.hasNext()) {
            return null;
        }

        ASTNode a = aFunc.hoc();
        if (a != null) {
            return a;
        }

        return bFunc.hoc();
    }
}


























