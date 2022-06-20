package parser.ast;

import parser.utils.ParseException;
import parser.utils.PeekTokenIterator;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public class Block extends Stmt{

    public Block() {
        super(ASTNodeTypes.BLOCK, "block");
    }

    public static ASTNode parse(PeekTokenIterator it) throws ParseException {
        it.nextMatch("{");
        var block = new Block();
        ASTNode stmt = null;
        while ((stmt = Stmt.parseStmt(it)) != null) {
            block.addChild(stmt);
        }
        it.nextMatch("}");
        return block;

    }
}
