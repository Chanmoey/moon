package parser.ast;

import lexer.Token;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author Chanmoey
 * @date 2022年05月26日
 */
@Getter
@Setter
public abstract class ASTNode {

    /**
     * 一棵树的定义
     */
    protected ArrayList<ASTNode> children = new ArrayList<>();
    protected ASTNode parent;

    /**
     * 其他信息
     */
    // 词法单元
    protected Token lexeme;
    // 标签（备注）
    protected String label;
    // 类型
    protected ASTNodeTypes type;

    protected ASTNode() {

    }

    protected ASTNode(ASTNodeTypes type, String label) {
        this.type = type;
        this.label = label;
    }

    public ASTNode getChild(int index) {
        // 越界问题由ArrayList来兜底。
        return this.children.get(index);
    }

    public void addChild(ASTNode node) {
        node.parent = this;
        this.children.add(node);
    }

    public Token getLexeme() {
        return this.lexeme;
    }

    public List<ASTNode> getChildren() {
        return this.children;
    }

    public String print(ASTNode astNode) {
        if (astNode == null) {
            return "Empty Tree!";
        }
        List<List<String>> treeList = new ArrayList<>();
        Deque<Object[]> queue = new ArrayDeque<>();
        queue.add(new Object[]{astNode, 0});
        while (!queue.isEmpty()) {
            Object[] cur = queue.removeFirst();
            int level = (Integer) cur[1];
            ASTNode node = (ASTNode) cur[0];
            if (level == treeList.size()) {
                treeList.add(new ArrayList<>());
            }
            treeList.get(level).add(node.label);

            for (ASTNode child : node.children) {
                queue.add(new Object[]{child, level + 1});
            }
        }

        StringBuilder sb = new StringBuilder();
        for (List<String> strings : treeList) {
            for (String string : strings) {
                sb.append(string).append("    ");
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}
































