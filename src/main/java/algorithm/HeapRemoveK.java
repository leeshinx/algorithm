package algorithm;

import java.util.PriorityQueue;

/**
 * @author: Feng.Lee
 * 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过K，并且K相对于数组长度来说比较小。
 * @createDate: 2022/1/5
 * @version: 1.0
 */
public class HeapRemoveK {

    // 策略 构建一个K节点的小根堆，弹出一个，插入一个，一直到小根堆全部弹出
    // 未测试 -------------
    public static void heapKSort(int[] arr, int k) {
        if (arr == null || arr.length < 2) {
            return;
        }
        PriorityQueue<Integer> heap = new PriorityQueue();
        // 初始K节点小根堆
        for (int i = 0; i < k; i ++ ) {
            heap.add(arr[i]);
        }
        int heapEndIdx = k - 1;
        int heapStartIdx = 0;
        // 添加一个到小根堆，再弹出一个
        while (heapEndIdx <= arr.length) {
            heap.add(heapEndIdx++);
            arr[heapStartIdx++] = heap.poll();
        }
        int heapSize = heap.size();
        // 弹出所有的小根堆
        for (int i = 0; i < heapSize; i++ ) {
            arr[heapStartIdx+i] = heap.poll();
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
