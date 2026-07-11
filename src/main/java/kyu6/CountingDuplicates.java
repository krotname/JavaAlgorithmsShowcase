package kyu6;


import java.util.function.Function;
import java.util.stream.Collectors;


public class CountingDuplicates {

    //6

    public static int duplicateCountStream(String text) {
        return (int) text.chars()
                .mapToObj(i -> Character.toLowerCase((char) i))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .count();
    }

    public static int duplicateCount(String text) {
        int result = 0;
        long[] characterCounts = new long[Character.MAX_VALUE + 1];

        for (char current : text.toCharArray()) {
            characterCounts[Character.toLowerCase(current)]++;
        }
        for (long count : characterCounts) {
            if (count > 1) {
                result++;
            }
        }
        return result;
    }

    /**
     * Count the number of Duplicates
     * Write a function that will return the count of distinct case-insensitive alphabetic characters and
     * numeric digits that occur more than once in the input string. The input string can be assumed to
     * contain only alphabets (both uppercase and lowercase) and numeric digits.
     * <p>
     * Example
     * "abcde" -> 0 # no characters repeats more than once
     * "aabbcde" -> 2 # 'a' and 'b'
     * "aabBcde" -> 2 # 'a' occurs twice and 'b' twice (`b` and `B`)
     * "indivisibility" -> 1 # 'i' occurs six times
     * "Indivisibilities" -> 2 # 'i' occurs seven times and 's' occurs twice
     * "aA11" -> 2 # 'a' and '1'
     * "ABBA" -> 2 # 'A' and 'B' each occur twice
     */


}
