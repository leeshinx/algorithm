package algorithm;

/**
 * @author: Feng.Lee
 * @createDate: 2021/12/27
 * @version: 1.0
 */
public class OddTimesNums {

    // arr中有两种数出现奇数次
    public static void printOddTimesNums(int[] arr) {
        int eor = 0;
        for (int i =0 ; i < arr.length; i ++) {
            eor ^= arr[i];
        }
        // 提取eor最右的二进制位1
        int rightOne = eor & (-eor);

        int onlyOne = 0;
        for (int i = 0; i < arr.length; i++) {
            // 跟eor最右一样的二进制位1
            if ((arr[i] & rightOne) != 0) {
                onlyOne ^= arr[i];
            }
        }
        System.out.println(onlyOne + " " + (onlyOne ^ eor));
    }
}
