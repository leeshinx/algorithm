package slidingwindow;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: Feng.Lee
 * @createDate: 2021/12/17
 * @version: 1.0
 */
public class Sort {

    // 范围
    public static int[] randomArray(int maxLength, int maxRange) {
        int length = new Random().nextInt(maxLength + 1);
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = new Random().nextInt(maxRange + 1);
        }
        return arr;
    }


    public static void main(String[] args) {
        int times = 1000;
        int maxLength = 20;
        int maxRange = 20;
        for (int i = 0; i < times; i++) {
            int[] array = randomArray(maxLength, maxRange);
            int[] copy = Arrays.copyOf(array, array.length);
            System.out.println("排序之前：" + Arrays.toString(array));
            radixSort(array);
            System.out.println("算法排序：" + Arrays.toString(array));
            Arrays.sort(copy);
            System.out.println("内置排序：" + Arrays.toString(copy));
            if (!Arrays.equals(array, copy)) {
                System.out.println("--------fail-------");
                return;
            }
        }

        System.out.println("--------success-------");
    }

    // 基数排序 在（计数排序和桶排序上优化）
    public static void radixSort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }

        radix(arr, 0, arr.length -1, maxDigits(arr));
    }

    public static void radix(int[] arr, int L, int R, int digits){
        int[] help = new int[R - L + 1];
        for (int i = 0; i < digits; i++) {
            int[] count = new int[10];
            int pow = (int)Math.pow(10, i);
            for (int n = L; n <= R; n++) {
                int x = (arr[n] / pow) % 10;
                count[x]++;
            }
            for (int j = 1; j < count.length; j++) {
                count[j] += count[j - 1];
            }
            for (int f = R; f >= L; f--) {
                int x = (arr[f] / pow) % 10;
                int index = --count[x];
                help[index] = arr[f];
            }
            for (int g = 0; g < help.length; g++) {
                arr[g + L] = help[g];
            }
        }
    }

    public static int maxDigits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = Math.max(max, i);
        }
        int digits = 0;
        while (max != 0) {
            digits++;
            max = max / 10;
        }
        return digits;
    }

    // 堆排序
    public static void heapSort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }
        // 构建大根堆 O(N*logN) 从上至下构建堆
//        for (int i = 1 ; i < arr.length; i++) { // O(N)
//            heapInsert(arr, i); //O(logN)
//        }
        // 构建大根堆 O(N) 从下至上构建堆 ， 叶子节点占一半
        for (int i = arr.length - 1 ; i >= 0; i--) { // O(N)
            heapify(arr, i, arr.length); //收敛于 O(1)
        }

        // 一个一个推出最大值
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        // O(N*logN)
        while (heapSize > 0) { // O(N)
            heapify(arr, 0, heapSize); // O(logN)
            swap(arr, 0, --heapSize);
        }
    }

    // 添加 完全二叉树 大根堆
    private static void heapInsert (int[] arr, int index) {
        while(index > 0 && arr[index] > arr[(index - 1) >> 1]) {
            swap(arr, index, (index - 1) >> 1);
            index = (index - 1) >> 1;
        }
    }

    // 从index位置，往下沉
    private static void heapify(int[] arr, int index, int heapSize) {
        // 交换值下沉 直到满足 大根堆
        int left = (index << 1) + 1;
        while (left < heapSize) {
            int right = left + 1;
            int largest = right < heapSize && arr[right] > arr[left] ? right : left;
            int maxIndex = arr[index] < arr[largest] ? largest : index;
            if (maxIndex == index) {
                break;
            }
            swap(arr, index, largest);
            index = largest;
            left = (index << 1) + 1;
        }
    }


    // 快速排序
    public static void quiltSort(int[] arr) {
        if (arr == null && arr.length <= 1) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    private static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // 随机快速排序
        // 划分值正好在 partition 后的中间区域 那它的复杂度是靠近O(NlogN),如果划分值越偏它的复杂度是靠近O(N^2)
        swap(arr, (new Random().nextInt(R - L + 1) + L), R);
        int[] equalE = partition(arr, L, R);
        process1(arr, L, equalE[0] - 1);
        process1(arr, equalE[1] + 1, R);
    }

    // 荷兰国旗问题
    private static int[] partition(int[] arr, int L, int R) {
        int lessR = L - 1;
        int index = L;
        int moreR = R;
        while (index < moreR) {
            if (arr[index] < arr[R]) {
                swap(arr, ++lessR, index++);
            } else if (arr[index] <= arr[R]) {
                swap(arr, --moreR, index);
            } else {
                // 跟划分值相等的 归纳到中间区域
                index++;
            }
        }
        swap(arr, moreR, R);
        return new int[]{lessR + 1, moreR};
    }

    // 归并排序 这是递归写法，  可以使用步长使用while循环完成
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        // int mid = (l + r) / 2 可能会越界
        // l 加上 l 和 r 的差的的 一半
        int mid = left + ((right - left) >> 1);
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    // 归并排序
    public static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 谁没越界 把谁剩下的copy到help中
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= right) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[left + j] = help[j];
        }
    }


    // 插入排序
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }

        for (int cur = 1; cur < arr.length; cur++) {

            for (int pre = cur - 1; pre >= 0; pre--) {
                if (arr[pre] > arr[cur]) {
                    swap(arr, pre, cur);
                }
            }
        }
    }


    // 选择排序
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int mixNumIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                mixNumIndex = arr[j] < arr[mixNumIndex] ? j : mixNumIndex;
            }
            if (mixNumIndex != i) {
                swap(arr, i, mixNumIndex);
            }
        }
    }

    // 冒泡排序
    public static void bubbleSort(int[] arr) {
        // 边界条件
        if (arr == null || arr.length == 1) {
            return;
        }

        // 0-4 0-3 0-2
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 1; j < length - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                }
            }
        }
    }


    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // i 和 j在同一个位置的话会出错
    private static void swap1(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}
