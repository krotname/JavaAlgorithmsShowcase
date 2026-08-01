package interview;


import java.util.List;
import java.util.Objects;


public class MaximumSequenceWithOneZero {
    /**
     * Дан массив из 0 и 1. Надо вывести максимальную последовательность 1 в этом массиве, при условии,
     * что она может быть разделена только одним 0. Пример вывода: 1,0,1,1,1,0,0,1,1,1 -> 4.
     */
    public static int maximumSequenceWithOneZero(List<Integer> b) {
        if (Objects.isNull(b) || b.isEmpty()) return 0;
        if (b.size() == 1 && b.get(0) == 1) return 1;
        if (b.size() == 1 && b.get(0) == 0) return 0;

        int maxCount = 0;
        int previousCount = 0;
        int currentCount = 0;

        for (Integer value : b) {
            if (value == 1) {
                currentCount++;
                maxCount = Math.max(maxCount, previousCount + currentCount);
            } else {
                previousCount = currentCount;
                currentCount = 0;
            }
        }
        return maxCount;
    }




}
