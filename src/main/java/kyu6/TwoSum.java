package kyu6;

import java.util.Arrays;

public class TwoSum {

    // 6: https://www.codewars.com/kata/52c31f8e6605bcc646000082/train/java
    public static int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if ((long) numbers[i] + numbers[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 3, 1}, 4)));
    }
}
