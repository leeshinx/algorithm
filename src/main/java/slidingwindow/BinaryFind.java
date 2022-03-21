package slidingwindow;

/**
 * @author: Feng.Lee
 * @createDate: 2021/12/20
 * @version: 1.0
 */
public class BinaryFind {

    public static void main(String[] args) {
        int testTimes = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] array = generateRandomArray(20, 5);
            print(array);
        }

    }

    // 对数器 - 随机数组
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int size = (int)(Math.random() * (maxSize + 1));
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int)(Math.random() * (maxValue + 1)) - (int)(Math.random() * maxValue);
        }
        return arr;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "  ");
            if (arr[i] == -5) {
                System.out.println("===================");
            }
        }
        System.out.println();
    }


    // arr 保证有序
    public static int binaryFind (int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int n = arr.length;
        int left = 0;
        int right = n -1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] == value) {
                return mid;
            }
            else if (arr[mid] > value) {
                right = mid -1;
            }
            else{
                left = mid + 1;
            }
        }
        return -1;
    }
}
