package parser.ast;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public class DeclareStmt extends Stmt{
    public DeclareStmt(ASTNode parent) {
        super(parent, ASTNodeTypes.DECLARE_STMT, "declare");
    }
}
