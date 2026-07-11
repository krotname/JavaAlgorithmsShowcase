package other;


import java.util.Locale;
import java.util.stream.Collectors;


public class AdjustCase {
    public static final String CO = "Со";
    public static final String C = "С";
    public static final String EMPTY = "";

    public String adjustCaseToLower(String string) {
        if (string == null || string.length() == 0) return string;
        if (string.length() == 1) return string.toUpperCase(Locale.ROOT);
        var stringLover = string.toLowerCase(Locale.ROOT).substring(1);
        var firstChar = string.substring(0, 1).toUpperCase(Locale.ROOT);
        return firstChar + stringLover;
    }

    public String adjustCaseStream(String string) {
        if (string == null || string.length() == 0) return string;
        var lower = string.chars()
                .mapToObj(i -> (char) i)
                .map(Object::toString)
                .map(value -> value.toLowerCase(Locale.ROOT))
                .collect(Collectors.joining())
                .substring(1);
        return string.chars()
                .mapToObj(i -> (char) i)
                .map(Object::toString)
                .findFirst()
                .orElse("")
                .toUpperCase(Locale.ROOT) + lower;
    }

    public String adjustCaseFor(String string) {
        if (string == null) return null;
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(i == 0) {
                chars[i] = Character.toUpperCase(chars[i]);
            }
            else {
                chars[i] = Character.toLowerCase(chars[i]);
            }
        }
        return String.valueOf(chars);
    }





}
