package algorithm.Class11;

/**
 * @author: Feng.Lee
 * 打印折纸痕的 凹凸
 * @createDate: 2022/1/26
 * @version: 1.0
 */
public class Code05_OrigamiPrint {


    public static void printOrigami(int n) {
        if (n <= 0) {
            return;
        }
        print(n, true);
    }
    // 打印凹凸
    private static void print(int n, boolean concave) {
        if (n <= 0) {
            return;
        }
        print(n -1, true);
        System.out.print(concave ? "凹 " : "凸 ");
        print(n -1, false);
    }


    public static void main(String[] args) {
        printOrigami(4);
        System.out.println();
    }
}
