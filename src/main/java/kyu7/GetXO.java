package kyu7;



public class GetXO {

    //7

    public static boolean getXO(String str) {

        int xCount = counterStr(str, "x");
        int yCount = counterStr(str, "o");
        return xCount == yCount;

    }

    public static int counterStr(String str, String target) {
        if (str == null || target == null || target.isEmpty()) {
            throw new IllegalArgumentException("input and target must not be null or empty");
        }
        int result = 0;
        char expected = Character.toLowerCase(target.charAt(0));
        for (char element : str.toCharArray()) {
            if (Character.toLowerCase(element) == expected) {
                result++;
            }
        }
        return result;
    }

    /**
     * Check to see if a string has the same amount of 'x's and 'o's. The method must return a boolean
     * and be case insensitive. The string can contain any char.
     * <p>
     * Examples input/output:
     * <p>
     * XO("ooxx") => true
     * XO("xooxx") => false
     * XO("ooxXm") => true
     * XO("zpzpzpp") => true // when no 'x' and 'o' is present should return true
     * XO("zzoo") => false
     */

}
