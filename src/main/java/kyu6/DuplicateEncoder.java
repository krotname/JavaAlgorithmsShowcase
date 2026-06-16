package kyu6;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class DuplicateEncoder {

    //6

    public static String encodeStream(String word) {
        Map<Character, AtomicInteger> countChars = word.chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.toMap(
                        Character::toLowerCase,
                        c -> new AtomicInteger(1),
                        (count1, count2) -> new AtomicInteger(2)));

        return word.chars()
                .mapToObj(i -> (char) i)
                .map(c -> countChars.get(Character.toLowerCase(c)).intValue() == 2 ? ")" : "(")
                .collect(Collectors.joining());
    }

    public static String encode(String word) {
        StringBuilder result = new StringBuilder();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : word.toCharArray()
        ) {
            map.putIfAbsent(Character.toLowerCase(c), 0);
            map.put(Character.toLowerCase(c), map.get(Character.toLowerCase(c)) + 1);
        }
        for (char c : word.toCharArray()
        ) {
            if (map.get(Character.toLowerCase(c)) > 1) {
                result.append(")");
            } else {
                result.append("(");
            }
        }
        return result.toString();
    }

    /**
     * The goal of this exercise is to convert a string to a new string where each character
     * in the new string is "(" if that character appears only once in the original string, or ")"
     * if that character appears more than once in the original string. Ignore capitalization when
     * determining if a character is a duplicate.
     * <p>
     * Examples
     * "din"      =>  "((("
     * "recede"   =>  "()()()"
     * "Success"  =>  ")())())"
     * "(( @"     =>  "))(("
     */


}
