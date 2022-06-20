package parser.ast;

import parser.utils.ParseException;
import parser.utils.PeekTokenIterator;

/**
 * @author Chanmoey
 * @date 2022年06月17日
 */
public class Program extends Block {
    public Program() {
        super();
    }

    public static ASTNode parse(PeekTokenIterator it) throws ParseException {
        var block = new Program();
        ASTNode stmt = null;
        while ((stmt = Stmt.parseStmt(it)) != null) {
            block.addChild(stmt);
        }
        return block;
    }
}
