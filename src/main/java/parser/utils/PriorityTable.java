package parser.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Chanmoey
 * @date 2022年05月29日
 */
public class PriorityTable {

    private final List<List<String>> table = new ArrayList<>();

    public PriorityTable() {
        table.add(Arrays.asList("&", "|", "^"));
        table.add(Arrays.asList("==", "!=", ">", "<", ">=", "<="));
        table.add(Arrays.asList("+", "-"));
        table.add(Arrays.asList("*", "/"));
        table.add(Arrays.asList("<<", ">>"));
    }

    public int size() {
        return table.size();
    }

    public List<String> get(int level) {
        return table.get(level);
    }
}
