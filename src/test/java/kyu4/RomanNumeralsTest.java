package kyu4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

@Tag("smoke")
class RomanNumeralsTest {

    @ParameterizedTest
    @MethodSource("romanCases")
    void shouldConvertIntegerToRoman(int value, String roman) {
        assertEquals(roman, RomanNumerals.toRoman(value));
    }

    @ParameterizedTest
    @MethodSource("romanCases")
    void shouldConvertRomanToInteger(int value, String roman) {
        assertEquals(value, RomanNumerals.fromRoman(roman));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 4, 9, 40, 90, 400, 900, 944, 1_666, 1_990, 2_008, 3_999})
    void shouldRoundTripCanonicalRomanNumerals(int value) {
        assertEquals(value, RomanNumerals.fromRoman(RomanNumerals.toRoman(value)));
    }

    @ParameterizedTest
    @MethodSource("invalidRomanNumerals")
    void shouldRejectInvalidRomanNumerals(String romanNumeral) {
        assertThrows(IllegalArgumentException.class, () -> RomanNumerals.fromRoman(romanNumeral));
    }

    @Test
    void shouldRejectResultsOutsideIntegerRange() {
        String romanNumeral = "M".repeat(2_147_484);

        assertThrows(ArithmeticException.class, () -> RomanNumerals.fromRoman(romanNumeral));
    }

    private static Stream<Arguments> romanCases() {
        return Stream.of(
                Arguments.of(1, "I"),
                Arguments.of(4, "IV"),
                Arguments.of(9, "IX"),
                Arguments.of(40, "XL"),
                Arguments.of(90, "XC"),
                Arguments.of(400, "CD"),
                Arguments.of(900, "CM"),
                Arguments.of(1_666, "MDCLXVI"),
                Arguments.of(1_990, "MCMXC"),
                Arguments.of(2_008, "MMVIII"),
                Arguments.of(3_999, "MMMCMXCIX")
        );
    }

    private static Stream<String> invalidRomanNumerals() {
        return Stream.of((String) null, "", "A", "MCMX?", "IIII", "VX", "MMMM");
    }
}
