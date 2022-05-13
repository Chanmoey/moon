package lexer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author Chanmoey
 * @date 2022年05月13日
 */
public class KeyWords {

    static String[] keywords = {
            "moon",
            "if",
            "else",
            "for",
            "while",
            "break",
            "def",
            "return"
    };

    static HashSet<String> set = new HashSet<>(List.of(keywords));

    public static boolean isKeyword(String word) {
        return set.contains(word);
    }
}
