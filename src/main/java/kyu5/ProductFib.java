package kyu5;




public class ProductFib {

    //5

    public static long[] productFib(long prod) {
        if (prod < 0) {
            throw new IllegalArgumentException("product must not be negative");
        }
        long first = 0L;
        long second = 1L;
        while (productIsLessThan(first, second, prod)) {
            long next = Math.addExact(first, second);
            first = second;
            second = next;
        }
        boolean exact = first == 0L ? prod == 0L : first <= prod / second && first * second == prod;
        return new long[]{first, second, exact ? 1L : 0L};
    }

    public static long getFibonacciValue(final long n) {
        if (n < 0) {
            throw new IllegalArgumentException("index must not be negative");
        }
        long first = 0L;
        long second = 1L;
        for (long i = 0L; i < n; i++) {
            if (i == n - 1L) {
                return second;
            }
            long next = Math.addExact(first, second);
            first = second;
            second = next;
        }
        return first;
    }

    private static boolean productIsLessThan(long first, long second, long target) {
        return first == 0L ? target > 0L : first <= target / second && first * second < target;
    }

    /**
     * The Fibonacci numbers are the numbers in the following integer sequence (Fn):
     * <p>
     * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, ...
     * <p>
     * such as
     * <p>
     * F(n) = F(n-1) + F(n-2) with F(0) = 0 and F(1) = 1.
     * <p>
     * Given a number, say prod (for product), we search two Fibonacci numbers F(n) and F(n+1) verifying
     * <p>
     * F(n) * F(n+1) = prod.
     * <p>
     * Your function productFib takes an integer (prod) and returns an array:
     * <p>
     * [F(n), F(n+1), true] or {F(n), F(n+1), 1} or (F(n), F(n+1), True)
     * depending on the language if F(n) * F(n+1) = prod.
     * <p>
     * If you don't find two consecutive F(n) verifying F(n) * F(n+1) = prodyou will return
     * <p>
     * [F(n), F(n+1), false] or {F(n), F(n+1), 0} or (F(n), F(n+1), False)
     * F(n) being the smallest one such as F(n) * F(n+1) > prod.
     * <p>
     * Some Examples of Return:
     * (depend on the language)
     * <p>
     * productFib(714) # should return (21, 34, true),
     * # since F(8) = 21, F(9) = 34 and 714 = 21 * 34
     * <p>
     * productFib(800) # should return (34, 55, false),
     * # since F(8) = 21, F(9) = 34, F(10) = 55 and 21 * 34 < 800 < 34 * 55
     * -----
     * productFib(714) # should return [21, 34, true],
     * productFib(800) # should return [34, 55, false],
     * -----
     * productFib(714) # should return {21, 34, 1},
     * productFib(800) # should return {34, 55, 0},
     * -----
     * productFib(714) # should return {21, 34, true},
     * productFib(800) # should return {34, 55, false},
     * Note:
     * You can see examples for your language in "Sample Tests"
     */


}
