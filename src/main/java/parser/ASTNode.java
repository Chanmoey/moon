package parser;

import lexer.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chanmoey
 * @date 2022年05月26日
 */
public abstract class ASTNode {

    /**
     * 一棵树的定义
     */
    protected ArrayList<ASTNode> children = new ArrayList<>();
    protected ASTNode parent;

    /**
     * 其他信息
     */
    protected Token lexeme;
    protected String label;
    protected ASTNodeTypes type;

    protected ASTNode(ASTNode parent) {
        this.parent = parent;
    }

    protected ASTNode(ASTNode parent, ASTNodeTypes type, String label) {
        this.parent = parent;
        this.type = type;
        this.label = label;
    }

    public ASTNode getChild(int index) {
        // 越界问题由ArrayList来兜底。
        return this.children.get(index);
    }

    public void addChild(ASTNode node) {
        this.children.add(node);
    }

    public Token getLexeme() {
        return lexeme;
    }

    public List<ASTNode> getChildren() {
        return children;
    }
}
































