package other;


import java.util.Locale;


public class AdjustCase {
    public static final String CO = "Со";
    public static final String C = "С";
    public static final String EMPTY = "";

    public String adjustCaseToLower(String string) {
        return capitalizeFirst(string);
    }

    public String adjustCaseStream(String string) {
        return capitalizeFirst(string);
    }

    public String adjustCaseFor(String string) {
        return capitalizeFirst(string);
    }

    private String capitalizeFirst(String string) {
        if (string == null || string.isEmpty()) {
            return string;
        }
        int firstEnd = string.offsetByCodePoints(0, 1);
        String first = string.substring(0, firstEnd);
        String lower = string.toLowerCase(Locale.ROOT);
        int loweredFirstLength = first.toLowerCase(Locale.ROOT).length();
        return first.toUpperCase(Locale.ROOT) + lower.substring(loweredFirstLength);
    }





}
