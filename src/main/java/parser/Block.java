package parser;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public class Block extends Stmt{

    public Block(ASTNode parent) {
        super(parent, ASTNodeTypes.BLOCK, "block");
    }
}
