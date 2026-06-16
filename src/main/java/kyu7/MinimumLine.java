package kyu7;


import java.util.Arrays;


public class MinimumLine {

    // 7 https://www.codewars.com/kata/5ac6932b2f317b96980000ca/train/java

    public static int minValue(int[] values) {
        return Arrays.stream(values)
                .sorted()
                .distinct()
                .reduce(0, (result, digit) -> result * 10 + digit);
    }

}
