package parser.utils;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import parser.ast.ASTNode;
import parser.ast.Factor;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Chanmoey
 * @date 2022年06月02日
 */
public class ParserUtils {

    public static String toPostfixExpression(ASTNode node) {

        if (node instanceof Factor) {
            return node.getLexeme().getValue();
        }

        var prts = new ArrayList<String>();
        for (var child : node.getChildren()) {
            prts.add(toPostfixExpression(child));
        }

        var lexemeStr = node.getLexeme() != null ? node.getLexeme().getValue() : "";
        if (lexemeStr.length() > 0) {
            return StringUtils.join(prts, " ") + " " + lexemeStr;
        } else {
            return StringUtils.join(prts, " ");
        }
    }

    public static String toBFSString(ASTNode root, int max) {

        var queue = new LinkedList<ASTNode>();
        var list = new ArrayList<String>();
        queue.add(root);

        int c = 0;
        while (!queue.isEmpty() && c++ < max) {
            var node = queue.poll();
            list.add(node.getLabel());
            queue.addAll(node.getChildren());
        }
        return StringUtils.join(list, " ");
    }
}
