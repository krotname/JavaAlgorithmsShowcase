package kyu6;


import java.util.Arrays;


public class SortTheOdd {

    // 6 https://www.codewars.com/kata/578aa45ee9fd15ff4600090d/train/java

    public static int[] sortArray(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("array must not be null");
        }

        int[] result = array.clone();
        int[] sortedOdds = Arrays.stream(array)
                .filter(value -> Math.floorMod(value, 2) == 1)
                .sorted()
                .toArray();
        int oddIndex = 0;
        for (int i = 0; i < result.length; i++) {
            if (Math.floorMod(result[i], 2) == 1) {
                result[i] = sortedOdds[oddIndex++];
            }
        }
        return result;
    }

    public static int[] sortArrayStream(int[] array) {
        return sortArray(array);
    }


}
