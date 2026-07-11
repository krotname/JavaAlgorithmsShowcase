package kyu6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@Tag("smoke")
class ArmstrongNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 7, 153, 370, 371, 407, 9474})
    void shouldAcceptNarcissisticNumbers(int number) {
        assertTrue(ArmstrongNumber.isNarcissistic(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 10, 100, 9475, Integer.MAX_VALUE})
    void shouldRejectNonNarcissisticNumbers(int number) {
        assertFalse(ArmstrongNumber.isNarcissistic(number));
    }

    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "9, 1",
            "10, 2",
            "99, 2",
            "100, 3",
            "9999, 4",
            "10000, 5",
            "100000000, 9"
    })
    void shouldCountDecimalDigits(int number, int expectedDigits) {
        assertEquals(expectedDigits, ArmstrongNumber.numberOfDigits(number));
    }
}
