package other;

// Выводит результат умножения цифр положительного числа в цикле, пока не останется однозначное число



public class Persist {


    public int persistence(long n) {
        int result = 0;
        while (n > 9) {
            char[] s = String.valueOf(n).toCharArray();
            int temp = Character.digit(s[0], 10);
            for (int i = 1; i < s.length; i++) {
                temp *= Character.digit(s[i], 10);
            }
            n = temp;
            result++;
        }
        return result;
    }
}
