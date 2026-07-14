package kyu7;



public class CountTheDigit {

    public static int nbDig(int n, int d) {
        if (n < 0 || d < 0 || d > 9) {
            throw new IllegalArgumentException("n must be non-negative and d must be a decimal digit");
        }
        long count = 0;
        for (long i = 0L; i <= n; i++) {
            String s = Long.toString(i * i);
            count += s.chars().filter(ch -> ch == Character.forDigit(d, 10)).count();
        }
        return Math.toIntExact(count);
    }

}
