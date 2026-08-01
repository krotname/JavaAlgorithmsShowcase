package kyu5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@Tag("smoke")
class ResistorColorCodes2Test {

    @ParameterizedTest
    @MethodSource("resistorCases")
    void shouldEncodeOhmValuesToColorBands(String ohms, String expectedBands) {
        assertEquals(expectedBands, ResistorColorCodes2.encodeResistorColors(ohms));
    }

    @Test
    void shouldRejectMalformedInput() {
        assertEquals("", ResistorColorCodes2.encodeResistorColors(null));
        assertEquals("", ResistorColorCodes2.encodeResistorColors("47"));
        assertEquals("", ResistorColorCodes2.encodeResistorColors("47 ohm"));
        assertEquals("", ResistorColorCodes2.encodeResistorColors("abc ohms"));
        assertEquals("", ResistorColorCodes2.encodeResistorColors("-1 ohms"));
        assertEquals("", ResistorColorCodes2.encodeResistorColors("1e309 ohms"));
    }

    private static Stream<Arguments> resistorCases() {
        return Stream.of(
                Arguments.of("10 ohms", "brown black black gold"),
                Arguments.of("22 ohms", "red red black gold"),
                Arguments.of("33 ohms", "orange orange black gold"),
                Arguments.of("47 ohms", "yellow violet black gold"),
                Arguments.of("55 ohms", "green green black gold"),
                Arguments.of("68 ohms", "blue gray black gold"),
                Arguments.of("99 ohms", "white white black gold"),
                Arguments.of("100 ohms", "brown black brown gold"),
                Arguments.of("4.7k ohms", "yellow violet red gold"),
                Arguments.of("10k ohms", "brown black orange gold"),
                Arguments.of("100k ohms", "brown black yellow gold"),
                Arguments.of("1M ohms", "brown black green gold"),
                Arguments.of("10M ohms", "brown black blue gold"),
                Arguments.of("990M ohms", "white white violet gold")
        );
    }
}
