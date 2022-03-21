package algorithm.Class13;

import entity.Employee;

/**
 * @author: Feng.Lee
 * 派对的最大快乐值，每个节点都有正数快乐值，某个节点来，那它的父节点和子节点不能来
 * @createDate: 2022/3/9
 * @version: 1.0
 */
public class Code03_MaxHappyTree {


    private static class Info {
        // x节点没来的最大快乐值
        private int no;
        // x节点来了的最大快乐值
        private int yes;

        public Info(int no, int yes) {
            this.no = no;
            this.yes = yes;
        }
    }


    /**
     * 选中节点x，x节点不来的时候最大快乐值
     * x节点来的时候最大快乐值
     * 从这两个节点中获取最大的值
     */
    public static int getMaxHappy (Employee boss) {
        if (boss == null) {
            return 0;
        }

        Info info = process(boss);
        return Math.max(info.no, info.yes);
    }

    private static Info process(Employee emp) {
        if (emp == null) {
            return new Info(0, 0);
        }

        int no = 0;
        int yes = emp.happy;

        if (emp.nexts != null && emp.nexts.size() > 0) {

            for (Employee employee : emp.nexts) {
                Info info = process(employee);
                no += Math.max(info.no, info.yes);
                yes += info.no;
            }
        }
        return new Info(no, yes);
    }
}
