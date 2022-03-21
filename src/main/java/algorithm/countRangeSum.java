package algorithm;

import java.util.Arrays;

/**
 * @author: Feng.Lee
 * 327. 区间和的个数
 * https://leetcode-cn.com/problems/count-of-range-sum/
 * @createDate: 2021/12/29
 * @version: 1.0
 */
public class countRangeSum {

    public static void main(String[] args) {
//        int times = 1000;
//        int maxLength = 20;
//        int maxRange = 20;
//        for (int i = 0; i < times; i++) {
//            int[] array = GenerateRandomArray.randomArray(maxLength, maxRange);
//            int[] copy = Arrays.copyOf(array, array.length);
//            int sum = countRangeSum(array, 0, 0);
//            int count =
//            System.out.println("比对：" + Arrays.toString(array));
//            if (sum != count) {
//                System.out.println("--------fail-------");
//                break;
//            }
//        }
//        System.out.println("--------success-------");

        int df = -2147483647 + -2147483647;

        int[] arr = {-2147483647,0,-2147483647,2147483647};
        int countRangeSum = countRangeSum(arr, -564 ,3864);
        System.out.println(countRangeSum);

    }

    public static int countRangeSum(int[] arr, int lower, int upper) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        long[] prefixSumArr = prefixSum(arr);
        System.out.println(Arrays.toString(prefixSumArr));
        int process = process(prefixSumArr, 0, prefixSumArr.length - 1, lower, upper);
        System.out.println(Arrays.toString(prefixSumArr));
        return process;
    }

    private static int process(long[] arr, int L, int R, int lower, int upper) {
        if (L == R) {
            if (arr[L] >= lower && arr[L] <= upper) {
                return 1;
            }
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        int leftCount = process(arr, L, mid, lower, upper);
        System.out.println("----------------leftCount--------------  " + leftCount);
        int rightCount = process(arr, mid + 1, R, lower, upper);
        System.out.println("----------------rightCount--------------  " + rightCount);
        int mergeCount = merge(arr, L, mid, R, lower, upper);
        System.out.println("----------------merge end--------------  "  + mergeCount);

        int total = leftCount + rightCount + mergeCount;
        System.out.println("----------------process--------------  "  + total);
        System.out.println();
        return total;
    }

    private static int merge(long[] arr, int L, int mid, int R, int lower, int upper) {
        System.out.println("L:" + L + "  mid:" + mid + "  R:" + R + "  lower:" + lower + "  upper:" + upper);
        // 两边对比 指针
        int winl = L;
        int winU = L;
        int count = 0;
        for (int i = mid + 1; i <= R; i++) {
            long min = arr[i] - upper;
            long max = arr[i] - lower;
            System.out.println("min:" + min + "  max:" + max);
            while (winU <= mid && arr[winU] <= max) {
                winU++;
                System.out.println("arr:" + arr[winU] + "  winU:" + winU);
            }
            while (winl <= mid && arr[winl] < min) {
                winl++;
                System.out.println("arr:" + arr[winl] + "  winl:" + winl);
            }
            count += winU - winl;
            System.out.println("ddd:" + count);
        }

        int left = L;
        int rigth = mid + 1;
        long[] help = new long[R - L + 1];
        int i = 0;
        while (left <= mid && rigth <= R) {
            help[i++] = arr[left] <= arr[rigth] ? arr[left++] : arr[rigth++];
        }
        while (left <= mid) {
            help[i++] = arr[left++];
        }
        while (rigth <= R) {
            help[i++] = arr[rigth++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
        // 归并
        return count;
    }
    // 因为可能会溢出 所以转成long类型
    public static long[] prefixSum(int[] arr) {
        long[] sum = new long[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        return sum;
    }

}
