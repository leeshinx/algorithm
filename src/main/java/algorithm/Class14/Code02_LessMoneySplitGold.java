package algorithm.Class14;

import java.util.PriorityQueue;

/**
 * @author: Feng.Lee
 * 需要把一整根金条，分成对应的几段，每次分都需要金条长度的钱 作为手续费
 * 求最少的手续费
 * @createDate: 2022/3/9
 * @version: 1.0
 */
public class Code02_LessMoneySplitGold {

    /**
     * 贪心算法
     * 我需求数组放入 小根堆
     * 把切金条时间倒放， 每次都用需求最小的切，那手续费就是最少
     */
    public static int lessMoney(int[] arr) {
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i : arr) {
            pQ.add(i);
        }
        // 手续费
        int sum = 0;
        // 当前金条长度
        int cur = 0;
        while (pQ.size() > 1) {
            cur = pQ.poll() + pQ.poll();
            sum += cur;
            pQ.add(cur);
        }
        return sum;
    }

}
