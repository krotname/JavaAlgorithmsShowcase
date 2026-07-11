package kyu7;



public class SumOddNumbers {

    //7 https://www.codewars.com/kata/55fd2d567d94ac3bc9000064/train/java

    public static int rowSumOddNumbers(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("row number must be positive");
        }
        long square = Math.multiplyExact((long) n, n);
        return Math.toIntExact(Math.multiplyExact(square, n));
    }

}
