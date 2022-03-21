package slidingwindow;

/**
 * @author: Feng.Lee
 * 浅醉和
 * @createDate: 2021/12/20
 * @version: 1.0
 */
public class ArraySum {

    public static void main (String[] args) {
        int[] arr = {1,1,1,1,1,1,1,1,1,2};
        int[] sum = arraySum(arr);
        for (int i : sum) {
            System.out.println(i);
        }
    }

    public static int[] arraySum(int[] arr){
        if (arr == null) {
            return null;
        }
        int n = arr.length;
        int[] sum = new int[n];

        sum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }

        return sum;
    }
}
