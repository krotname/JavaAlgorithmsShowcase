package kyu3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@Tag("smoke")
class RailFenceCipherTest {

    @Test
    void shouldEncodeAndDecodeKnownExample() {
        String encoded = RailFenceCipher.encode("WEAREDISCOVEREDFLEEATONCE", 3);

        assertEquals("WECRLTEERDSOEEFEAOCAIVDEN", encoded);
        assertEquals("WEAREDISCOVEREDFLEEATONCE", RailFenceCipher.decode(encoded, 3));
    }

    @ParameterizedTest
    @MethodSource("roundTripCases")
    void shouldRoundTripPlainTextForDifferentRails(String text, int rails) {
        String encoded = RailFenceCipher.encode(text, rails);

        assertEquals(text, RailFenceCipher.decode(encoded, rails));
    }

    @ParameterizedTest
    @MethodSource("invalidRailCounts")
    void shouldRejectInvalidRailCounts(int rails) {
        assertThrows(IllegalArgumentException.class,
                () -> RailFenceCipher.encode("ABC", rails));
        assertThrows(IllegalArgumentException.class,
                () -> RailFenceCipher.decode("ABC", rails));
    }

    private static Stream<Arguments> roundTripCases() {
        return Stream.of(
                Arguments.of("", 2),
                Arguments.of("Hello, World!", 4),
                Arguments.of("Rail fence cipher keeps punctuation.", 5)
        );
    }

    private static Stream<Arguments> invalidRailCounts() {
        return Stream.of(
                Arguments.of(-1),
                Arguments.of(0),
                Arguments.of(1)
        );
    }
}
