package kyu7;



public class GetSum {

    //7 https://www.codewars.com/kata/55f2b110f61eb01779000053/train/java

    public static int getSum(int a, int b) {
        long lower = Math.min(a, b);
        long upper = Math.max(a, b);
        long count = upper - lower + 1L;
        long sum = (lower + upper) * count / 2L;
        return Math.toIntExact(sum);
    }

}
