package parser.utils;

import parser.ast.ASTNode;

/**
 * High order function: 高阶函数。
 *
 * @author Chanmoey
 * @date 2022年05月29日
 */
@FunctionalInterface
public interface ExprHOF {

    ASTNode hoc() throws ParseException;
}
