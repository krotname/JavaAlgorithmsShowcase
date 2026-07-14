package kyu6;


import java.util.Locale;


public class toCamelCase {

    //6 https://www.codewars.com/kata/517abf86da9663f1d2000003/train/java

    public static String toCamelCase(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        String[] split = s.split("[-_]", -1);
        for (String segment : split) {
            if (segment.isEmpty()) {
                throw new IllegalArgumentException("delimiters must separate non-empty words");
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(split[0]);
        for (int i = 1; i < split.length; i++) {
            stringBuilder.append(split[i].substring(0, 1).toUpperCase(Locale.ROOT)).append(split[i].substring(1));
        }
        return stringBuilder.toString();
    }

    /**
     * Complete the method/function so that it converts dash/underscore delimited words into camel casing.
     * The first word within the output should be capitalized only if the original word was capitalized
     * (known as Upper Camel Case, also often referred to as Pascal case).
     */

}
