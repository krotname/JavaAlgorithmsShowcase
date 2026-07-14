package kyu7;


import java.util.Arrays;
import java.util.Locale;


public class IsAnagram {

    public static boolean isAnagram(String test, String original) {
        char[] testChars = test.toLowerCase(Locale.ROOT).toCharArray();
        char[] originalChars = original.toLowerCase(Locale.ROOT).toCharArray();
        Arrays.sort(testChars);
        Arrays.sort(originalChars);
        return Arrays.equals(testChars, originalChars);
    }

}
