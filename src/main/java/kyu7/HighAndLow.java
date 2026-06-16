package kyu7;


import static common.SafeParse.parseInt;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;


public class HighAndLow {
    //7 https://www.codewars.com/kata/554b4ac871d6813a03000035/train/java

    public static String highAndLow(String numbers) {
        IntSummaryStatistics statistics = parseNumbers(numbers)
                .mapToInt(Integer::intValue)
                .summaryStatistics();
        if (statistics.getCount() == 0) {
            throw new IllegalArgumentException("numbers must not be empty");
        }
        return statistics.getMax() + " " + statistics.getMin();
    }

    public static Integer high(String numbers) {
        return parseNumbers(numbers)
                .max(Comparator.naturalOrder())
                .orElseThrow();
    }

    private static java.util.stream.Stream<Integer> parseNumbers(String numbers) {
        if (numbers == null || numbers.trim().isEmpty()) {
            return java.util.stream.Stream.empty();
        }
        return Arrays.stream(numbers.trim().split("\\s+")).map(HighAndLow::parseNumber);
    }

    private static Integer parseNumber(String value) {
        return parseInt(value);
    }


}
