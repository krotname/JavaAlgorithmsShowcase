package other;


import java.util.List;


public class RecursionMax {

    public static int recursionMax(List<Integer> array) {
        return max(array);
    }

    public static int max(List<Integer> array) {
        if (array == null) {
            return -1;
        }
        if (array.isEmpty()) {
            return -1;
        }
        if (array.size() == 1) {
            return array.get(0);
        }
        int max = Integer.MIN_VALUE;
        for (int a : array
        ) {
            if (a > max) {
                max = a;
            }
        }
        return max;
    }


}
