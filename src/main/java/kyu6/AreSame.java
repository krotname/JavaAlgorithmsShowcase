package kyu6;


import java.util.Arrays;



public class AreSame {

    //6 https://www.codewars.com/kata/550498447451fbbd7600041c/train/java

    public static boolean comp(int[] a, int[] b) {
        return a != null && b != null && Arrays.equals(
                Arrays.stream(a)
                        .mapToLong(i -> (long) i * i)
                        .sorted()
                        .toArray(),
                Arrays.stream(b)
                        .mapToLong(i -> i)
                        .sorted()
                        .toArray());
    }



}
