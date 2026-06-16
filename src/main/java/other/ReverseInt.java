package other;


import static common.SafeParse.parseInt;


public class ReverseInt {

    public static int reverse(int x) {
        if (x >= 0) {
            return parseInt(new StringBuilder(String.valueOf(x)).reverse().toString());
        } else {
            return parseInt(new StringBuilder(String.valueOf(x).substring(1)).reverse().toString()) * -1;
        }
    }

}
