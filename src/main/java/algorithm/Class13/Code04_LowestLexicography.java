package algorithm.Class13;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: Feng.Lee
 * 获取最小的字符串
 * @createDate: 2022/3/9
 * @version: 1.0
 */
public class Code04_LowestLexicography {

    private static class MyComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    // 贪心算法，使用合适的贪心规则
    // 为什么不能  o1.compareTo(o2) ps: ["b", "ba"]  "bba">"bab"
    public static String lowestString (String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        Arrays.sort(strs, new MyComparator());
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }
}
