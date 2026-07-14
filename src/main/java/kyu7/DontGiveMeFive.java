package kyu7;



public class DontGiveMeFive {

    //7 https://www.codewars.com/kata/5813d19765d81c592200001a/solutions/java

    public static int dontGiveMeFive(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("start must not be greater than end");
        }
        long count;
        if (end < 0) {
            long largestMagnitude = -(long) start;
            long smallestMagnitude = -(long) end;
            count = countWithoutFive(largestMagnitude) - countWithoutFive(smallestMagnitude - 1L);
        } else if (start >= 0) {
            count = countWithoutFive(end) - countWithoutFive((long) start - 1L);
        } else {
            count = countWithoutFive(-(long) start) - 1L + countWithoutFive(end);
        }
        return Math.toIntExact(count);
    }

    private static long countWithoutFive(long upperBound) {
        if (upperBound < 0L) {
            return 0L;
        }
        char[] digits = Long.toString(upperBound).toCharArray();
        long count = 0L;
        for (int position = 0; position < digits.length; position++) {
            int digit = Character.digit(digits[position], 10);
            int allowedSmallerDigits = digit - (digit > 5 ? 1 : 0);
            count += allowedSmallerDigits * powerOfNine(digits.length - position - 1);
            if (digit == 5) {
                return count;
            }
        }
        return count + 1L;
    }

    private static long powerOfNine(int exponent) {
        long result = 1L;
        for (int i = 0; i < exponent; i++) {
            result *= 9L;
        }
        return result;
    }

}
