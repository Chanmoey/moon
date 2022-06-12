package parser;

import lexer.Lexer;
import lexer.LexicalException;
import org.junit.jupiter.api.Test;
import parser.ast.ASTNode;
import parser.ast.Expr;
import parser.utils.ParseException;
import parser.utils.ParserUtils;
import parser.utils.PeekTokenIterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Chanmoey
 * @date 2022年06月02日
 */
class ParseExprTests {

    @Test
    void simple() throws LexicalException, ParseException {
        var expr = createExpr("1 + 1 + 1");
        assertEquals("1 1 1 + +", ParserUtils.toPostfixExpression(expr));
    }

    private ASTNode createExpr(String src) throws LexicalException, ParseException {
        var lexer = new Lexer();
        var tokens = lexer.analyse(src.chars().mapToObj(x -> (char) x));
        var tokenIt = new PeekTokenIterator(tokens.stream());
        return Expr.parse(tokenIt);
    }
}
