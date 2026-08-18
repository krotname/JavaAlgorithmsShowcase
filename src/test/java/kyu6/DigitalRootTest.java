package kyu6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Tag("smoke")
class DigitalRootTest {

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "7, 7",
            "16, 7",
            "132189, 6",
            "493193, 2",
            "999999, 9"
    })
    void shouldReduceNumberToSingleDigit(int value, int expected) {
        assertEquals(expected, DigitalRoot.digitalRoot(value));
        assertEquals(expected, DigitalRoot.digitalRootRecursiveStream(value));
        assertEquals(expected, DigitalRoot.digital_root(value));
    }
}
