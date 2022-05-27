package parser;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public class IfStmt extends Stmt{
    public IfStmt(ASTNode parent) {
        super(parent, ASTNodeTypes.IF_STMT, "if");
    }
}
