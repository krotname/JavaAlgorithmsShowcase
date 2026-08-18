package interview;

import static interview.MaximumSequenceWithOneZero.maximumSequenceWithOneZero;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("smoke")
class MaximumSequenceWithOneZeroTest {

    @Test
    void shouldHandleNullEmptyAndSingleElementInputs() {
        assertEquals(0, maximumSequenceWithOneZero(null));
        assertEquals(0, maximumSequenceWithOneZero(List.of()));
        assertEquals(1, maximumSequenceWithOneZero(List.of(1)));
        assertEquals(0, maximumSequenceWithOneZero(List.of(0)));
    }

    @Test
    void shouldFindLongestOnesSequenceSeparatedByOneZero() {
        assertEquals(4, maximumSequenceWithOneZero(List.of(1, 0, 1, 1, 1, 0, 0, 1, 1, 1)));
    }

    @Test
    void shouldSplitSequenceOnTwoConsecutiveZeros() {
        assertEquals(3, maximumSequenceWithOneZero(List.of(1, 1, 0, 0, 1, 1, 1)));
    }

    @Test
    void shouldNotCountAcrossMultipleSeparatedZeros() {
        assertEquals(2, maximumSequenceWithOneZero(List.of(1, 0, 1, 0, 1)));
    }

    @Test
    void shouldCountAllOnesWhenThereIsNoZeroSeparator() {
        assertEquals(5, maximumSequenceWithOneZero(List.of(1, 1, 1, 1, 1)));
    }
}
