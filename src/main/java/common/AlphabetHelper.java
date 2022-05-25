package common;

import java.util.regex.Pattern;

/**
 * @author Chanmoey
 * @date 2022年05月13日
 */
public class AlphabetHelper {

    private AlphabetHelper(){}

    private static final Pattern LETTER = Pattern.compile("^[a-zA-Z]$");

    private static final Pattern NUMBER = Pattern.compile("^\\d$");

    private static final Pattern LITERAL = Pattern.compile("^\\w$");

    private static final Pattern OPERATOR = Pattern.compile("^[,;+\\-\\\\*<>=!&|^%/]$");

    public static boolean isLetter(char c) {
        return LETTER.matcher(String.valueOf(c)).matches();
    }

    public static boolean isNumber(char c) {
        return NUMBER.matcher(String.valueOf(c)).matches();
    }

    public static boolean isLiteral(char c) {
        return LITERAL.matcher(String.valueOf(c)).matches();
    }

    public static boolean isOperator(char c) {
        return OPERATOR.matcher(String.valueOf(c)).matches();
    }
}
