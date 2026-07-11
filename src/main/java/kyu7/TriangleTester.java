package kyu7;



public class TriangleTester {

    //7 https://www.codewars.com/kata/56606694ec01347ce800001b/train/java

    public static boolean isTriangle(int a, int b, int c) {
        return a > 0 && b > 0 && c > 0
                && (long) a + b > c
                && (long) a + c > b
                && (long) b + c > a;
    }

}
