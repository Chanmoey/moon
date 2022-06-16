package parser;

import lexer.Lexer;
import lexer.LexicalException;
import org.junit.jupiter.api.Test;
import parser.ast.DeclareStmt;
import parser.utils.ParseException;
import parser.utils.ParserUtils;
import parser.utils.PeekTokenIterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Chanmoey
 * @date 2022年06月16日
 */
class StmtTests {

    @Test
    void declare() throws ParseException, LexicalException {
        var it = createTokenIt("moon i = 100 * 2");
        var stmt = DeclareStmt.parse(null, it);
        assertEquals(ParserUtils.toPostfixExpression(stmt), "i 100 2 * =");
        System.out.println(stmt.print(stmt));
    }

    private PeekTokenIterator createTokenIt(String src) throws LexicalException {
        var lexer = new Lexer();
        var tokens = lexer.analyse(src.chars().mapToObj(x -> (char) x));
        return new PeekTokenIterator(tokens.stream());
    }
}
