package parser.ast;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public class DefStmt extends Stmt{
    protected DefStmt(ASTNode parent) {
        super(ASTNodeTypes.DEF_STMT, "def");
    }
}
