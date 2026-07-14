package kyu5;



public class HumanReadableTime {

    //5 https://www.codewars.com/kata/52685f7382004e774f0001f7/train/java

    /**
     * Write a function, which takes a non-negative integer (seconds) as input and returns the time
     * in a human-readable format (HH:MM:SS)
     * <p>
     * HH = hours, padded to 2 digits, range: 00 - 99
     * MM = minutes, padded to 2 digits, range: 00 - 59
     * SS = seconds, padded to 2 digits, range: 00 - 59
     * The maximum time never exceeds 359999 (99:59:59)
     * <p>
     * You can find some examples in the test fixtures.
     */

    private static final int SIXTY = 60;

    public static String makeReadable(int seconds) {
        if (seconds < 0 || seconds > 359999) {
            throw new IllegalArgumentException("seconds must be between 0 and 359999");
        }
        int HH = 0;
        int MM = 0;
        int SS = 0;

        final int FACTOR_MM = SIXTY;
        final int FACTOR_HH = SIXTY * SIXTY;

        HH = seconds / FACTOR_HH;
        seconds = seconds % FACTOR_HH;
        MM = seconds / FACTOR_MM;
        SS = seconds % FACTOR_MM;

        return addZero(HH) + ":" + addZero(MM) + ":" + addZero(SS);
    }

    private static String addZero(int i) {
        if (i > 9 || i < 0) {
            return "" + i;
        } else {
            return "0" + i;
        }
    }

}
