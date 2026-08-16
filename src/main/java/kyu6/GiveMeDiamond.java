package kyu6;



public class GiveMeDiamond {

    //6 https://www.codewars.com/kata/5503013e34137eeeaa001648/train/java

    public static String print(int n) {
        if (n % 2 == 0 || n < 1) return null;
        int count = n / 2 + 1;
        StringBuilder strings = new StringBuilder();
        for (int i = 1; i <= count; i++) {
            strings.append(" ".repeat(count - i)).append("*".repeat(i * 2 - 1)).append('\n');
        }
        for (int i = count - 1; i >= 1; i--) {
            strings.append(" ".repeat(count - i)).append("*".repeat(i * 2 - 1)).append('\n');
        }
        return strings.toString();
    }





}
