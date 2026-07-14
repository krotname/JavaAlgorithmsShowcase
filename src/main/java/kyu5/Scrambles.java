package kyu5;


public class Scrambles {

    // 5 https://www.codewars.com/kata/55c04b4cc56a697bb0000048/train/java

    public static boolean scramble(String str1, String str2) {
        if (str1 == null || str2 == null) {
            throw new IllegalArgumentException("inputs must not be null");
        }
        if (str2.length() > str1.length()) {
            return false;
        }
        int[] available = new int[Character.MAX_VALUE + 1];
        for (int i = 0; i < str1.length(); i++) {
            available[str1.charAt(i)]++;
        }
        for (int i = 0; i < str2.length(); i++) {
            if (--available[str2.charAt(i)] < 0) {
                return false;
            }
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
