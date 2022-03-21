package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Feng.Lee
 * @createDate: 2022/1/18
 * @version: 1.0
 */
public class GenerateParenthesis {

    public static List<String> generateParenthesis(int n) {
        List<String> resList = new ArrayList<>();
        if (n == 1) {
            resList.add("()");
            return resList;
        }
        doGenerate(n -1, 1, resList, "(");
        return resList;
    }

    private static void doGenerate(int n, int i, List<String> resList, String res) {
        if (n < 0 || i < 0) {
            return ;
        }
        if (n == 0 && i == 0) {
            resList.add(res);
            return;
        }

        doGenerate(n -1, i + 1, resList, res + "(" );
        doGenerate(n, i -1, resList, res + ")");
    }

}
