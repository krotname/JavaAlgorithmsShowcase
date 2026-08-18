package algorithms.sprint0;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit")
class SlidingAverageTest {

    @Test
    void calculatesMovingAverageForSampleWindow() {
        assertListDoubles(SlidingAverage.movingAverage(7, List.of(1, 2, 3, 4, 5, 6, 7), 4),
                2.5, 3.5, 4.5, 5.5);
    }

    @Test
    void windowOfOneReturnsOriginalValues() {
        assertListDoubles(SlidingAverage.movingAverage(7, List.of(1, 2, 3, 4, 5, 6, 7), 1),
                1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void fullWindowReturnsSingleAverage() {
        assertListDoubles(SlidingAverage.movingAverage(7, List.of(1, 2, 3, 4, 5, 6, 7), 7), 4.0);
    }

    @Test
    void invalidWindowReturnsEmptyList() {
        assertEquals(List.of(), SlidingAverage.movingAverage(5, List.of(1, 2, 3, 4, 5, 6, 7), 6));
        assertEquals(List.of(), SlidingAverage.movingAverage(5, List.of(1, 2, 3, 4, 5), 0));
        assertEquals(List.of(), SlidingAverage.movingAverage(5, List.of(1, 2, 3, 4, 5), -3));
    }

    @Test
    void usesOnlyFirstNValues() {
        assertListDoubles(SlidingAverage.movingAverage(5, List.of(1, 2, 3, 4, 5, 6, 7), 3), 2.0, 3.0, 4.0);
    }

    @Test
    void emptyInputReturnsEmptyList() {
        assertEquals(List.of(), SlidingAverage.movingAverage(10, List.of(), 3));
    }

    @Test
    void largeValuesDoNotOverflowTheWindowSum() {
        int max = Integer.MAX_VALUE;
        assertListDoubles(SlidingAverage.movingAverage(4, List.of(max, max, max, max), 2), max, max, max);
    }

    @Test
    void extremeValuesDoNotOverflowTheRollingUpdate() {
        assertListDoubles(SlidingAverage.movingAverage(
                2, List.of(Integer.MIN_VALUE, Integer.MAX_VALUE), 1),
                Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static void assertListDoubles(List<Double> actual, double... expected) {
        assertEquals(expected.length, actual.size(), "size");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual.get(i), 1e-9, "idx=" + i);
        }
    }
}
