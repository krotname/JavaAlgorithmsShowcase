package other;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Tag("smoke")
class ReverseIntTest {

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "123, 321",
            "-123, -321",
            "120, 21",
            "-120, -21",
            "1000000001, 1000000001",
            "1534236469, 0",
            "-2147483648, 0"
    })
    void shouldReverseIntegerDigits(int input, int expected) {
        assertEquals(expected, ReverseInt.reverse(input));
    }
}
