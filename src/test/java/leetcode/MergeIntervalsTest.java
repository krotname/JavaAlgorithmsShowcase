package leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("smoke")
class MergeIntervalsTest {

    private final MergeIntervals merger = new MergeIntervals();

    @Test
    void shouldMergeOverlappingIntervalsEvenWhenInputIsUnsorted() {
        int[][] intervals = {{8, 10}, {1, 3}, {2, 6}, {15, 18}};

        assertArrayEquals(new int[][]{{1, 6}, {8, 10}, {15, 18}}, merger.merge(intervals));
    }

    @Test
    void shouldMergeTouchingBoundaries() {
        assertArrayEquals(new int[][]{{1, 5}}, merger.merge(new int[][]{{1, 4}, {4, 5}}));
    }

    @Test
    void shouldKeepSeparatedIntervals() {
        assertArrayEquals(new int[][]{{1, 2}, {4, 5}, {8, 9}}, merger.merge(new int[][]{{4, 5}, {1, 2}, {8, 9}}));
    }

    @Test
    void shouldReturnEmptyResultForEmptyInput() {
        assertArrayEquals(new int[0][0], merger.merge(new int[0][0]));
    }

    @Test
    void shouldRejectInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> merger.merge(null));
        assertThrows(IllegalArgumentException.class, () -> merger.merge(new int[][]{null}));
        assertThrows(IllegalArgumentException.class, () -> merger.merge(new int[][]{{1}}));
        assertThrows(IllegalArgumentException.class, () -> merger.merge(new int[][]{{2, 1}}));
        assertThrows(IllegalArgumentException.class, () -> merger.merge(new int[][]{{1, 2, 3}}));
    }
}
