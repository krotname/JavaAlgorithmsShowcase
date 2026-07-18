package kyu6;



public class TribonacciSequence {

    private static final int SIGNATURE_SIZE = 3;

    //6 https://www.codewars.com/kata/556deca17c58da83c00002db

    public static double[] tribonacci(double[] s, int n) {
        if (s == null || s.length != SIGNATURE_SIZE) {
            throw new IllegalArgumentException("signature must contain exactly three values");
        }
        if (n < 0) {
            throw new IllegalArgumentException("sequence length must not be negative");
        }
        double[] result = new double[n];
        int signatureLength = Math.min(SIGNATURE_SIZE, n);
        System.arraycopy(s, 0, result, 0, signatureLength);
        for (int i = SIGNATURE_SIZE; i < n; i++) {
            result[i] = result[i - 1] + result[i - 2] + result[i - 3];
        }
        return result;
    }

}
