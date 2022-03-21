package slidingwindow;

import java.util.HashMap;
import java.util.Map;

/** https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * @author: Feng.Lee
 * @createDate: 2021/5/27
 * @version: 1.0
 */
public class MinWindowSimple {

    public static int minWindow(String s) {
        int left = 0;
        int right = 0;

        Map<Character, Integer> window = new HashMap<>();
        int maxSize = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);

            while (window.get(c) > 1) {
                char c1 = s.charAt(left);
                left ++;
                window.put(c1, window.get(c1) - 1);
            }
            maxSize = maxSize > right - left ? maxSize : right - left;
        }

        return maxSize;
    }


    public static void main(String[] args) {
        int s = minWindow("bbbbbbbb");
        System.out.println(s);
    }
}
