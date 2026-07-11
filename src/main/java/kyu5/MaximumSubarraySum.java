package kyu5;


public class MaximumSubarraySum {

    //5

    public static int sequence(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("array must not be null");
        }
        long best = 0L;
        long current = 0L;
        for (int value : arr) {
            current = Math.max(0L, current + value);
            best = Math.max(best, current);
        }
        return Math.toIntExact(best);
    }

    /**
     * The maximum sum subarray problem consists in finding the maximum sum of a contiguous subsequence
     * in an array or list of integers:
     * <p>
     * Easy case is when the list is made up of only positive numbers and the maximum sum is the sum of the whole array.
     * If the list is made up of only negative numbers, return 0 instead.
     * <p>
     * Empty list is considered to have zero greatest sum. Note that the empty list or array is also a valid sublist/subarray.
     */

}
