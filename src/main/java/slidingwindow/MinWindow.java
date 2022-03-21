package slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 * @author: Feng.Lee
 * @createDate: 2021/5/27
 * @version: 1.0
 */
public class MinWindow {

    public static List<Integer> minWindow(String s, String t) {
        int left = 0;
        int right = 0;
        int valid = 0;

        Map<Character, Integer> need = new HashMap();
        for (char aChar : t.toCharArray()) {
            need.put(aChar, need.getOrDefault(aChar, 0) + 1);
        }

        Map<Character, Integer> window = new HashMap<>();

        List<Integer> result = new ArrayList<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);
            right++;
            if (need.containsKey(c) && need.get(c).equals(window.get(c))) {
                valid++;
            }

            while (right - left >= t.length()) {
                if (valid == need.size()) {
                    result.add(left);
                }
                char c1 = s.charAt(left);
                left ++;
                if (need.containsKey(c1) && need.get(c1).equals(window.get(c1))) {
                    valid--;
                }
                window.put(c1, window.get(c1) - 1);
            }
        }

        return result;
    }




    public static void main(String[] args) {
        List<Integer> result = minWindow("cbaebabacd", "abc");
        System.out.println(result);
    }
}
