package parser.ast;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public abstract class Stmt extends ASTNode{

    protected Stmt(ASTNode parent, ASTNodeTypes type, String label) {
        super(parent, type, label);
    }
}
