package other;


public class ReverseInt {

    public static int reverse(int x) {
        long reversed = Long.parseLong(new StringBuilder(String.valueOf(Math.abs((long) x))).reverse().toString());
        reversed = x < 0 ? -reversed : reversed;

        return reversed < Integer.MIN_VALUE || reversed > Integer.MAX_VALUE ? 0 : (int) reversed;
    }

}
