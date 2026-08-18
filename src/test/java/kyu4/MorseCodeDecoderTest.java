package kyu4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@Tag("smoke")
class MorseCodeDecoderTest {

    @ParameterizedTest
    @MethodSource("morseMessages")
    void shouldDecodeMorseMessages(String morse, String expected) {
        assertEquals(expected, MorseCodeDecoder.decodeMorse(morse));
    }

    @Test
    void shouldEncodePlainTextWithWordSeparators() {
        assertEquals(".... . -.--   .--- ..- -.. .", MorseCodeDecoder.encode("HEY JUDE"));
        assertEquals("... --- ...   .....", MorseCodeDecoder.encode("sos 5"));
        assertEquals("", MorseCodeDecoder.encode("   "));
        assertThrows(IllegalArgumentException.class, () -> MorseCodeDecoder.encode("hi?"));
    }

    @Test
    void shouldDecodeBitsUsingInferredSignalUnit() {
        String bits = "110011001100110000001100000011111100110011111100111111";

        assertEquals(".... . -.--", MorseCodeDecoder.decodeBits(bits));
        assertEquals("HEY", MorseCodeDecoder.decodeMorse(MorseCodeDecoder.decodeBits(bits)));
        assertEquals(".   .", MorseCodeDecoder.decodeBits("100000001"));
    }

    @Test
    void shouldHandleBitDecoderHelpers() {
        assertEquals("101", MorseCodeDecoder.trimStartZeros("000101"));
        assertEquals("", MorseCodeDecoder.trimStartZeros("000"));
        assertEquals(3, MorseCodeDecoder.checkDigits("111000111"));
        assertEquals(1, MorseCodeDecoder.checkDigits("101"));
        assertEquals(".", MorseCodeDecoder.decodeBits("0001000"));
        assertEquals("", MorseCodeDecoder.decodeBits("0000"));
    }

    static Stream<Arguments> morseMessages() {
        return Stream.of(
                Arguments.of(".... . -.--", "HEY"),
                Arguments.of("...---...", "SOS"),
                Arguments.of("  .... . -.--   .--- ..- -.. .  ", "HEY JUDE"),
                Arguments.of("---... -.-.-.", ":;"),
                Arguments.of("", "")
        );
    }
}
