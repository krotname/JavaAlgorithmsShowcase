package kyu6;


import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;


public class CamelCase {

    //6 https://www.codewars.com/kata/5208f99aee097e6552000148/train/java
    //6 https://www.codewars.com/kata/587731fda577b3d1b0001196/train/java

    private static final int A = 65; // ASCII
    private static final int Z = 90; // ASCII

    public static String fromCamelCase(String input) {
        if (input == null || input.isEmpty()) return "";
        char[] chars = input.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(chars[0]);
        for (int i = 0; i < input.length() - 1; i++) {
            if (chars[i + 1] >= A && chars[i + 1] <= Z) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(chars[i + 1]);
        }
        return stringBuilder.toString();
    }

    public static String toCamelCase(String str) {
        if (str == null || str.isEmpty()) return "";
        return Arrays.stream(str.split(" "))
                .filter(s -> s.length() > 0)
                .map(s -> s.substring(0, 1).toUpperCase(Locale.ROOT) + s.substring(1))
                .collect(Collectors.joining()).trim();
    }



}
