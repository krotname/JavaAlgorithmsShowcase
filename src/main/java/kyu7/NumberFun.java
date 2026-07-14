package kyu7;



public class NumberFun {

    public static long findNextSquare(long sq) {
        if (sq < 0L) {
            return -1L;
        }
        long sqrt = (long) Math.sqrt(sq);
        while (sqrt > 0L && sqrt > sq / sqrt) {
            sqrt--;
        }
        while (sqrt + 1L <= sq / (sqrt + 1L)) {
            sqrt++;
        }
        if (sqrt * sqrt != sq) {
            return -1L;
        }
        long next = Math.addExact(sqrt, 1L);
        return Math.multiplyExact(next, next);
    }

}
