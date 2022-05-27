package parser.ast;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public class Variable extends Factor{
    protected Variable(ASTNode parent) {
        super(parent, ASTNodeTypes.VARIABLE, "variable");
    }
}
