package algorithm;

/**
 * @author: Feng.Lee
 * 小和问题
 * @createDate: 2021/12/28
 * @version: 1.0
 */
public class SmailSum {


    // 右边比左边小
    // 右边两倍 比左边小。
    public static void main(String[] args) {

    }

    public static int mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }

        return parttion(arr, 0, arr.length);
    }


    // 右边两倍 比左边小 的 个数
    public static int parttion(int[] arr, int start, int end) {
        // basecase
        if (start == end) {
            return 0;
        }

        int mid = start + (end - start) >> 1;
        return parttion(arr, start, mid)
                + parttion(arr, mid + 1, end)
                + merge(arr, start, mid, end);
    }

    private static int merge(int[] arr, int start, int mid, int end) {
        int count = 0;
        // 右边两倍 比左边小 的 个数
        int winR = 0;
        int l = start;
        int r = mid + 1;
        while (l <= mid) {
            while (r + winR <= end && arr[l] > arr[r + winR] * 2) {
                winR++;
            }
            count += winR;
            l++;
        }

        int left = mid;
        int right = mid + 1;
        int[] help = new int[end - start + 1];
        int i = 0;
        while (left <= mid && right <= end) {
            help[i++] = arr[left] < arr[right] ? arr[left++] : arr[right++];
        }
        while (left <= mid) {
            help[i++] = arr[left++];
        }
        while (right <= end) {
            help[i++] = arr[right++];
        }
        for (int j = 0; j <= i; j++) {
            arr[start + j] = help[j];
        }
        return count;
    }



//    // 右边比左边小的个数
//    public static int parttion(int[] arr, int start, int end) {
//        // basecase
//        if (start == end) {
//            return 0;
//        }
//
//        int mid = start + (end - start) >> 1;
//        return parttion(arr, start, mid)
//                        + parttion(arr, mid + 1, end)
//                        + merge(arr, start, mid, end);
//    }
//
//    private static int merge(int[] arr, int start, int mid, int end) {
//        int count = 0;
//        // 从两端的 右边开始比，或者使用逆序比较
//        int left = mid;
//        int right = mid + 1;
//        int[] help = new int[end - start + 1];
//        int i = 0;
//        while (left <= mid && right <= end) {
//            // 逆序
//            count += arr[left] > arr[right] ? end - right + 1 : 0;
//            help[i++] = arr[left] > arr[right] ? arr[left++] : arr[right++];
//        }
//        while (left <= mid) {
//            help[i++] = arr[left++];
//        }
//        while (right <= end) {
//            help[i++] = arr[right++];
//        }
//        for (int j = 0; j <= i; j++) {
//            arr[start + j] = help[j];
//        }
//        return count;
//    }


//    // 小和问题
//    public static int parttion(int[] arr, int start, int end) {
//        // basecase
//        if (start == end) {
//            return 0;
//        }
//
//        int mid = start + (end -start) >> 1;
//        return
//        parttion(arr, start, mid)
//        + parttion(arr, mid + 1, end)
//        + merge(arr, start, mid, end);
//    }
//
//    private static int merge(int[] arr, int start, int mid, int end) {
//        int ans = 0;
//        int left = start;
//        int right = mid + 1;
//        int[] help = new int[end -start + 1];
//        int i = 0;
//        while (left <= mid && right <= end) {
//            ans += arr[left] < arr[right] ? arr[left] * (end - right + 1) : 0;
//            help[i++] = arr[left] < arr[right] ? arr[left++] : arr[right++];
//        }
//        while (left <= mid) {
//            help[i++] = arr[left++];
//        }
//        while (right <= end) {
//            help[i++] = arr[right++];
//        }
//        for (int j = 0; j <= i; j++) {
//            arr[start + j] = help[j];
//        }
//        return ans;
//    }

}
