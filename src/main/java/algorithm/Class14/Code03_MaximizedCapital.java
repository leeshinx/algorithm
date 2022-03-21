package algorithm.Class14;

import entity.Program;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: Feng.Lee
 * 每做完一个项目，马上获得收益，可以支持你去做下一个项目，不能并行的做项目
 * 求你最后获得的最大钱数
 * @createDate: 2022/3/9
 * @version: 1.0
 */
public class Code03_MaximizedCapital {

    /**
     * 使用一个小根堆，按花费排序，堆顶是投资最小
     * 使用的一个大根堆，按收益排序，堆顶是收益最大的
     * 按现有资金从小根堆获取可以投资的项目，放入大根堆中进行排序，再从大根堆获取就是最大收益
     */
    /**
     * k:可投资几个项目
     * w：起始资金
     * amount：每个项目的需要投资金额
     * profits：每个项目的纯收益
     */
    public static int findMaximizedCapital(int k, int w, int[] amount, int[] profits) {
        PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Program> maxProfitsQ = new PriorityQueue<>(new MaxProfitsComparator());
        for (int i = 0; i < amount.length; i++) {
            minCostQ.add(new Program(amount[i], profits[i]));
        }

        int result = w;
        for (int i = k; i >0; i--) {
            // 从小根堆中获取所有可以投资的项目，加入到大根堆
            while (!minCostQ.isEmpty() && minCostQ.peek().start < result) {
                maxProfitsQ.add(minCostQ.poll());
            }
            if (maxProfitsQ.isEmpty()) {
                return result;
            }
            result += maxProfitsQ.poll().end;
        }
        return result;
    }

    private static class MinCostComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.start - o2.start;
        }
    }

    private static class MaxProfitsComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o2.end - o1.end;
        }
    }
}
