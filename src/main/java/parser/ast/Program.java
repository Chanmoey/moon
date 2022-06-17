package parser.ast;

import parser.utils.ParseException;
import parser.utils.PeekTokenIterator;

/**
 * @author Chanmoey
 * @date 2022年06月17日
 */
public class Program extends Block{

    public Program(ASTNode parent) {
        super(parent);
    }

    public static ASTNode parse(ASTNode parent, PeekTokenIterator iterator) throws ParseException {
        var block = new Block(parent);
        ASTNode stmt;
        while ((stmt = Stmt.parseStmt(parent, iterator)) != null) {
            block.addChild(stmt);
        }
        return block;
    }
}
