package parser.ast;

import parser.utils.ParseException;
import parser.utils.PeekTokenIterator;

/**
 * @author Chanmoey
 * @date 2022年06月17日
 */
public class FunctionArgs extends ASTNode {
    protected FunctionArgs(ASTNode parent) {
        super(parent);
        this.label = "args";
    }

    public static ASTNode parse(ASTNode parent, PeekTokenIterator iterator) throws ParseException {

        var args = new FunctionArgs(parent);

        while (iterator.peek().isType()) {
            var type = iterator.next();
            var variable = (Variable) Factor.parse(parent, iterator);
            variable.setTypeLexeme(type);
            args.addChild(variable);

            if (!")".equals(iterator.peek().getValue())) {
                iterator.nextMatch(",");
            }
        }

        return args;
    }
}
