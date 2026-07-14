package kyu6;



public class TribonacciSequence {

    //6 https://www.codewars.com/kata/556deca17c58da83c00002db

    public static double[] tribonacci(double[] s, int n) {
        if (s == null || s.length != 3) {
            throw new IllegalArgumentException("signature must contain exactly three values");
        }
        if (n < 0) {
            throw new IllegalArgumentException("sequence length must not be negative");
        }
        double[] result = new double[n];
        int signatureLength = Math.min(s.length, n);
        System.arraycopy(s, 0, result, 0, signatureLength);
        for (int i = s.length; i < n; i++) {
            result[i] = result[i - 1] + result[i - 2] + result[i - 3];
        }
        return result;
    }

}
