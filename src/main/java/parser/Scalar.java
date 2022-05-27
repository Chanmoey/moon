package parser;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public class Scalar extends Factor{
    protected Scalar(ASTNode parent) {
        super(parent, ASTNodeTypes.SCALAR, "scalar");
    }
}
