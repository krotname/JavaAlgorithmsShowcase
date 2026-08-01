package other;


public class ReverseInt {

    public static int reverse(int x) {
        long reversed = 0;
        int remaining = x;
        while (remaining != 0) {
            reversed = reversed * 10 + remaining % 10;
            remaining /= 10;
        }

        return reversed < Integer.MIN_VALUE || reversed > Integer.MAX_VALUE ? 0 : (int) reversed;
    }

}
