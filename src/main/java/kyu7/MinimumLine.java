package kyu7;


import static common.SafeParse.parseUnsignedInt;

import java.util.Arrays;
import java.util.stream.Collectors;


public class MinimumLine {

    // 7 https://www.codewars.com/kata/5ac6932b2f317b96980000ca/train/java

    public static int minValue(int[] values) {
        String result = Arrays.stream(values)
                .sorted()
                .distinct()
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
        return parseUnsignedInt(result);
    }

}
