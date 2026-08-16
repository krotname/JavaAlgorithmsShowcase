package kyu6;



public class Prime {

    //6 https://www.codewars.com/kata/5262119038c0985a5b00029f/train/java

    public static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        if (num % 2 == 0) {
            return num == 2;
        }
        for (int divisor = 3; (long) divisor * divisor <= num; divisor += 2) {
            if (num % divisor == 0) {
                return false;
            }
        }
        return true;
    }




}
