package kyu7;


public class FindDivisor {

    //7 https://www.codewars.com/kata/53dbd5315a3c69eed20002dd/train/java

    public static long numberOFindDivisorivisors(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("number must be positive");
        }
        long count = 0L;
        for (int divisor = 1; divisor <= i / divisor; divisor++) {
            if (i % divisor == 0) {
                count += divisor == i / divisor ? 1L : 2L;
            }
        }
        return count;
    }

}
