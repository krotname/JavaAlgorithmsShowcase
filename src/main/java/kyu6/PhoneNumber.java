package kyu6;


import java.util.Locale;


public class PhoneNumber {

    //6

    public static String createPhoneNumber(int[] numbers) {
        if (numbers == null || numbers.length != 10) {
            throw new IllegalArgumentException("a phone number must contain exactly ten digits");
        }
        for (int digit : numbers) {
            if (digit < 0 || digit > 9) {
                throw new IllegalArgumentException("phone number elements must be decimal digits");
            }
        }
        return String.format(Locale.ROOT, "(%d%d%d) %d%d%d-%d%d%d%d", numbers[0], numbers[1], numbers[2], numbers[3],
                numbers[4], numbers[5], numbers[6], numbers[7], numbers[8], numbers[9]);
    }

    /**
     * Write a function that accepts an array of 10 integers (between 0 and 9), that returns
     * a string of those numbers in the form of a phone number.
     */

}
