package kyu7;


public class SquareDigit {

    private static final String MAX_INT = Integer.toString(Integer.MAX_VALUE);

    //7 https://www.codewars.com/kata/546e2562b03326a88e000020/train/java

    public static int squareDigits(int n) {
        if (n == 0) return 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (n != 0) {
            int i = n % 10;
            n /= 10;
            stringBuilder.insert(0, i * i);
        }
        String result = stringBuilder.toString();
        if (result.length() > MAX_INT.length()
                || result.length() == MAX_INT.length() && result.compareTo(MAX_INT) > 0) {
            return Integer.MAX_VALUE;
        }
        return Integer.parseInt(result);
    }

}
