package parser.utils;

import org.apache.commons.lang3.NotImplementedException;
import parser.ast.ASTNode;

/**
 * @author Chanmoey
 * @date 2022年06月02日
 */
public class ParserUtils {

    public static String toPostfixExpression(ASTNode node) {
        String leftStr;
        String rightStr;

        switch (node.getType()) {
            case BINARY_EXPR: {
                leftStr = toPostfixExpression(node.getChild(0));
                rightStr = toPostfixExpression(node.getChild(1));
                return leftStr + " " + rightStr + " " + node.getLexeme().getValue();
            }

            case VARIABLE:
            case SCALAR: {
                return node.getLexeme().getValue();
            }

            default:
                throw new NotImplementedException("Not this Expression.");
        }
    }
}
