package kyu5;


import java.math.BigInteger;


public class NumberOfTrailingZerosOfN {

    //5 https://www.codewars.com/kata/52f787eb172a8b4ae1000a34/train/java

    public static int zeros(int n) {
        return zeroOfTrailing(factorial(n));
    }

    private static int zeroOfTrailing(BigInteger l) {
        int count = 0;
        while (l.signum() != 0 && l.mod(BigInteger.TEN).signum() == 0) {
            count++;
            l = l.divide(BigInteger.TEN);
        }
        return count;
    }

    private static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

}
