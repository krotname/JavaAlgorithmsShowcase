package kyu6;



public class ArmstrongNumber {

    // 6 https://www.codewars.com/kata/5287e858c6b5a9678200083c/train/java
    public static boolean isNarcissistic(int number) {
        if (number < 0) {
            return false;
        }

        long result = 0L;
        int currentNumber = number;
        int power = numberOfDigits(number);
        do {
            int currentDigit = currentNumber % 10;
            currentNumber /= 10;
            result += pow(currentDigit, power);
        } while (currentNumber > 0);
        return result == number;
    }

    private static long pow(int base, int exponent) {
        long result = 1L;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }


    public static int numberOfDigits(int number) {
        var length = 1;
        if (number >= 100000000) {
            length += 8;
            number /= 100000000;
        }
        if (number >= 10000) {
            length += 4;
            number /= 10000;
        }
        if (number >= 100) {
            length += 2;
            number /= 100;
        }
        if (number >= 10) {
            length += 1;
        }
        return length;
    }



}
