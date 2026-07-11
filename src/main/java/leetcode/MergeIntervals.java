package leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class MergeIntervals {
    //Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return
    // an array of the non-overlapping intervals that cover all the intervals in the input.
    // https://leetcode.com/problems/merge-intervals/
    public int[][] merge(int[][] intervals) {
        if (intervals == null) {
            throw new IllegalArgumentException("Intervals must not be null");
        }
        if (intervals.length == 0) {
            return new int[0][0];
        }

        int[][] sortedIntervals = Arrays.stream(intervals)
                .map(MergeIntervals::copyInterval)
                .sorted(Comparator.comparingInt(interval -> interval[0]))
                .toArray(int[][]::new);

        List<int[]> mergedIntervals = new ArrayList<>();
        int[] current = sortedIntervals[0];

        for (int i = 1; i < sortedIntervals.length; i++) {
            int[] next = sortedIntervals[i];
            if (next[0] <= current[1]) {
                current[1] = Math.max(current[1], next[1]);
            } else {
                mergedIntervals.add(current);
                current = next;
            }
        }
        mergedIntervals.add(current);

        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }

    private static int[] copyInterval(int[] interval) {
        if (interval == null || interval.length != 2 || interval[0] > interval[1]) {
            throw new IllegalArgumentException("Each interval must contain start and end values");
        }
        return new int[]{interval[0], interval[1]};
    }
}
