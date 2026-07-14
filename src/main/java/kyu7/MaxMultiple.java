package kyu7;


public class MaxMultiple {

    //7 https://www.codewars.com/kata/5aba780a6a176b029800041c/train/java

    public static int maxMultiple(int divisor, int bound) {
        if (divisor <= 0 || bound < divisor) {
            throw new IllegalArgumentException("divisor must be positive and no greater than bound");
        }
        return bound - bound % divisor;
    }
}
