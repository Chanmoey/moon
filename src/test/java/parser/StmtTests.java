package parser;

import lexer.Lexer;
import lexer.LexicalException;
import org.junit.jupiter.api.Test;
import parser.ast.*;
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
        var stmt = DeclareStmt.parse(it);
        assertEquals("i 100 2 * =", ParserUtils.toPostfixExpression(stmt));
        System.out.println(stmt.print(stmt));
    }

    @Test
    void ifStmt() throws LexicalException, ParseException {
        var it = createTokenIt("if(a){" +
                "a = 1" +
                "}"
        );

        var stmt = (IfStmt) IfStmt.parse(it);
        var expr = (Variable) stmt.getChild(0);
        var block = (Block) stmt.getChild(1);
        var assignStmt = (AssignStmt) block.getChild(0);

        assertEquals("a", expr.getLexeme().getValue());
        assertEquals("=", assignStmt.getLexeme().getValue());
    }

    @Test
    void ifElseStmt() throws LexicalException, ParseException {
        var it = createTokenIt("if(a){" +
                "a = 1" +
                "} else {" +
                "a = 2" +
                "a = a * 3" +
                "}"
        );

        var stmt = (IfStmt) IfStmt.parse(it);
        var expr = (Variable) stmt.getChild(0);
        var block = (Block) stmt.getChild(1);
        var assignStmt = (AssignStmt) block.getChild(0);
        var elseBlock = (Block) stmt.getChild(2);
        var assignStmt2 = (AssignStmt) elseBlock.getChild(0);

        assertEquals("a", expr.getLexeme().getValue());
        assertEquals("=", assignStmt.getLexeme().getValue());
        assertEquals("=", assignStmt2.getLexeme().getValue());
        assertEquals(2, elseBlock.getChildren().size());
    }

    private PeekTokenIterator createTokenIt(String src) throws LexicalException {
        var lexer = new Lexer();
        var tokens = lexer.analyse(src.chars().mapToObj(x -> (char) x));
        return new PeekTokenIterator(tokens.stream());
    }
}
