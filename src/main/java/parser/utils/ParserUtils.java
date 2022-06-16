package parser.utils;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import parser.ast.ASTNode;
import parser.ast.Factor;

import java.util.ArrayList;

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
}
