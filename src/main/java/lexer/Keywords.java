package lexer;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Chanmoey
 * @date 2022年05月13日
 */
public class Keywords {
    static String[] keywords = {
            "var",
            "if",
            "else",
            "for",
            "while",
            "break",
            "func",
            "return",
            "int"
    };

    static HashSet<String> set = new HashSet<>(Arrays.asList(keywords));

    // 判断读取的字符串是否为关键字词
    public static boolean isKeyword(String word){
        return set.contains(word);
    }
}
