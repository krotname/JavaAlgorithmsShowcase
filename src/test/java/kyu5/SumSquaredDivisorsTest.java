package kyu5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Tag("smoke")
class SumSquaredDivisorsTest {

    @ParameterizedTest
    @CsvSource({
            "1, 1, '[[1, 1]]'",
            "1, 250, '[[1, 1], [42, 2500], [246, 84100]]'",
            "42, 250, '[[42, 2500], [246, 84100]]'",
            "250, 500, '[[287, 84100]]'"
    })
    void shouldListNumbersWhoseSquaredDivisorsSumToSquare(long from, long to, String expected) {
        assertEquals(expected, SumSquaredDivisors.listSquared(from, to));
    }

    @Test
    void shouldRejectInvalidOrUnrepresentableRangesWithoutLoopOverflow() {
        assertThrows(IllegalArgumentException.class, () -> SumSquaredDivisors.listSquared(0, 1));
        assertThrows(IllegalArgumentException.class, () -> SumSquaredDivisors.listSquared(2, 1));
        assertThrows(ArithmeticException.class,
                () -> SumSquaredDivisors.listSquared(Long.MAX_VALUE, Long.MAX_VALUE));
    }
}
