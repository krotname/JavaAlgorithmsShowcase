package kyu3;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void shouldDecodeWithoutAllocatingForUnusedRails() {
        assertEquals("", RailFenceCipher.decode("", Integer.MAX_VALUE));
        assertEquals("abc", RailFenceCipher.decode("abc", Integer.MAX_VALUE));
    }

    @ParameterizedTest
    @MethodSource("roundTripCases")
    void shouldRoundTripPlainTextForDifferentRails(String text, int rails) {
        String encoded = RailFenceCipher.encode(text, rails);

        assertEquals(text, RailFenceCipher.decode(encoded, rails));
    }

    private static Stream<Arguments> roundTripCases() {
        return Stream.of(
                Arguments.of("", 2),
                Arguments.of("Hello, World!", 4),
                Arguments.of("Rail fence cipher keeps punctuation.", 5)
        );
    }
}
