package kyu5;


import java.util.StringJoiner;


public class SumSquaredDivisors {

    //5 https://www.codewars.com/kata/55aa075506463dac6600010d/train/java

    public static String listSquared(long m, long n) {
        if (m < 1L || n < m) {
            throw new IllegalArgumentException("range must satisfy 1 <= m <= n");
        }
        StringJoiner result = new StringJoiner(", ", "[", "]");
        for (long value = m; ; value++) {
            long sumSquare = sumSquaredDivisors(value);
            if (isPerfectSquare(sumSquare)) {
                result.add("[" + value + ", " + sumSquare + "]");
            }
            if (value == n) {
                break;
            }
        }
        return result.toString();
    }

    private static long sumSquaredDivisors(long value) {
        long sum = 0L;
        for (long divisor = 1L; divisor <= value / divisor; divisor++) {
            if (value % divisor == 0L) {
                long pairedDivisor = value / divisor;
                sum = Math.addExact(sum, Math.multiplyExact(divisor, divisor));
                if (pairedDivisor != divisor) {
                    sum = Math.addExact(sum, Math.multiplyExact(pairedDivisor, pairedDivisor));
                }
            }
        }
        return sum;
    }

    private static boolean isPerfectSquare(long value) {
        if (value < 0L) {
            return false;
        }
        long root = (long) Math.sqrt(value);
        while (root > 0L && root > value / root) {
            root--;
        }
        while (root + 1L <= value / (root + 1L)) {
            root++;
        }
        return root * root == value;
    }

    /**
     * 1, 246, 2, 123, 3, 82, 6, 41 are the divisors of number 246. Squaring these divisors we get:
     * 1, 60516, 4, 15129, 9, 6724, 36, 1681. The sum of these squares is 84100 which is 290 * 290.
     * <p>
     * Task
     * Find all integers between m and n (m and n integers with 1 <= m <= n) such that the sum of
     * their squared divisors is itself a square.
     * <p>
     * We will return an array of subarrays or of tuples (in C an array of Pair) or a string. The subarrays
     * (or tuples or Pairs) will have two elements: first the number the squared divisors of which is a square
     * and then the sum of the squared divisors.
     * <p>
     * Example:
     * list_squared(1, 250) --> [[1, 1], [42, 2500], [246, 84100]]
     * list_squared(42, 250) --> [[42, 2500], [246, 84100]]
     * The form of the examples may change according to the language, see "Sample Tests".
     * <p>
     * Note
     * In Fortran - as in any other language - the returned string is not permitted to contain any redundant trailing
     * whitespace: you can use dynamically allocated character strings.
     */

}
