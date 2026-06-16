package kyu7;


import static common.SafeParse.parseInt;


public class SquareDigit {

    //7 https://www.codewars.com/kata/546e2562b03326a88e000020/train/java

    public static int squareDigits(int n) {
        if (n == 0) return 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (n != 0) {
            int i = n % 10;
            n /= 10;
            stringBuilder.insert(0, i * i);
        }
        return parseInt(stringBuilder.toString());
    }

}
