package kyu6;



public class DigitalRoot {

    //6

    public static int digitalRootRecursiveStream(int n) {
        return n < 10 ? n : digitalRootRecursiveStream(String.valueOf(n)
                .chars()
                .map(i -> Character.digit((char) i, 10))
                .sum());
    }

    public static int digitalRoot(int n) {
        if (n < 10) return n;
        int temp = n;
        int result;
        do {
            result = 0;
            while (temp > 0) {
                result = result + temp % 10;
                temp /= 10;
            }
            temp = result;
        } while (result > 9);
        return result;
    }

    /**
     * Digital root is the recursive sum of all the digits in a number.
     * Given n, take the sum of the digits of n. If that value has more than one
     * digit, continue reducing in this way until a single-digit number is produced.
     * The input will be a non-negative integer.
     */


}
