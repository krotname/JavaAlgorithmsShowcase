package kyu6;


public class DigPow {

    public static long digPow(int n, int p) {
        if (n <= 0 || p <= 0) {
            throw new IllegalArgumentException("n and p must be positive");
        }
        long sum = 0L;
        long exponent = p;
        for (char digit : Integer.toString(n).toCharArray()) {
            sum = Math.addExact(sum, powExact(Character.digit(digit, 10), exponent));
            exponent++;
        }
        return sum % n == 0 ? sum / n : -1;
    }

    private static long powExact(long base, long exponent) {
        long result = 1L;
        long factor = base;
        long remaining = exponent;
        while (remaining > 0L) {
            if ((remaining & 1L) == 1L) {
                result = Math.multiplyExact(result, factor);
            }
            remaining >>>= 1;
            if (remaining > 0L) {
                factor = Math.multiplyExact(factor, factor);
            }
        }
        return result;
    }

}
