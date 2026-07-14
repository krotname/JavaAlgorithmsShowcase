package other;


import java.util.List;


// Просчитывает варианты numDigits`значных чисел в которых сумма чисел равна sumDigits и цифры идут по порядку
// и выводит массив количество найденых цифр, первое и последние

public class HowManyNumbers {

    public List<Long> findAll(final int sumDigits, final int numDigits) {
        if (numDigits <= 0 || numDigits > 18 || sumDigits < 0) {
            throw new IllegalArgumentException("numDigits must be between 1 and 18 and sumDigits must be non-negative");
        }
        if (sumDigits < numDigits || sumDigits > 9 * numDigits) {
            return List.of();
        }
        long[] summary = new long[3];
        collect(0, numDigits, 1, sumDigits, 0L, summary);
        return summary[0] == 0L ? List.of() : List.of(summary[0], summary[1], summary[2]);
    }

    private static void collect(int position, int totalDigits, int minimumDigit,
                                int remainingSum, long value, long[] summary) {
        if (position == totalDigits) {
            if (remainingSum == 0) {
                summary[0]++;
                if (summary[0] == 1L) {
                    summary[1] = value;
                }
                summary[2] = value;
            }
            return;
        }
        int positionsAfter = totalDigits - position - 1;
        for (int digit = minimumDigit; digit <= 9; digit++) {
            int nextSum = remainingSum - digit;
            if (nextSum < digit * positionsAfter || nextSum > 9 * positionsAfter) {
                continue;
            }
            long nextValue = Math.addExact(Math.multiplyExact(value, 10L), digit);
            collect(position + 1, totalDigits, digit, nextSum, nextValue, summary);
        }
    }

}
