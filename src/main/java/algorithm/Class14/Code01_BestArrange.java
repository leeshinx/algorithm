package algorithm.Class14;

import entity.Program;

import java.util.Comparator;

/**
 * @author: Feng.Lee
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目
 * 给你每个项目的开始时间和结束时间
 * 要求会议室进行的宣讲的场次最多
 * 返回最多宣讲场次
 * @createDate: 2022/3/9
 * @version: 1.0
 */
public class Code01_BestArrange {

    private static class ProgramComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    // 贪心算法，按结束时间排序， 为了更多宣讲场次，选的会议越早结束越好
    public static int bestArrange (Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }

        int timeLine = 0;
        int result = 0;
        for (Program program : programs) {
            if (timeLine <= program.start) {
                result++;
                timeLine = program.end;
            }
        }
        return result;
    }
}
