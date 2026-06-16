package kyu5;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



public class Scrambles {

    // 5 https://www.codewars.com/kata/55c04b4cc56a697bb0000048/train/java

    public static boolean scramble(String str1, String str2) {
        List<Character> list1 = str1.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toCollection(ArrayList::new));
        List<Character> list2 = str2.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        for (char c2 : list2
        ) {
            if (!list1.remove(Character.valueOf(c2))) return false;
        }
        return true;
    }

    /**
     * Complete the function scramble(str1, str2) that returns true if a portion of str1 characters can be rearranged
     * to match str2, otherwise returns false.
     * <p>
     * Notes:
     * <p>
     * Only lower case letters will be used (a-z). No punctuation or digits will be included.
     * Performance needs to be considered
     * Input strings s1 and s2 are null terminated.
     * Examples
     * scramble('rkqodlw', 'world') ==> True
     * scramble('cedewaraaossoqqyt', 'codewars') ==> True
     * scramble('katas', 'steak') ==> False
     */


}
