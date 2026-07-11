package kyu5;


public class NumberOfTrailingZerosOfN {

    //5 https://www.codewars.com/kata/52f787eb172a8b4ae1000a34/train/java

    public static int zeros(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must not be negative");
        }
        int zeroCount = 0;
        for (int factor = 5; factor <= n; ) {
            zeroCount += n / factor;
            if (factor > n / 5) {
                break;
            }
            factor *= 5;
        }
        return zeroCount;
    }

}
