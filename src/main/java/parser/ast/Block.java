package parser.ast;

import parser.utils.ParseException;
import parser.utils.PeekTokenIterator;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public class Block extends Stmt{

    public Block(ASTNode parent) {
        super(parent, ASTNodeTypes.BLOCK, "block");
    }

    public static  ASTNode parse(ASTNode parent, PeekTokenIterator iterator) throws ParseException {
        iterator.nextMatch("{");
        var block = new Block(parent);
        ASTNode stmt;
        while ((stmt = Stmt.parseStmt(parent, iterator)) != null) {
            block.addChild(stmt);
        }
        iterator.nextMatch("}");

        return block;
    }
}
