package parser.ast;

/**
 * @author Chanmoey
 * @date 2022年05月27日
 */
public class ForStmt extends Stmt {

    protected ForStmt(ASTNode parent) {
        super(ASTNodeTypes.FOR_STMT, "for");
    }
}
