package parser.ast;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public abstract class Factor extends ASTNode{
    protected Factor(ASTNode parent, ASTNodeTypes type, String label) {
        super(parent, type, label);
    }
}
