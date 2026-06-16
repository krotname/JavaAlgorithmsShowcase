package common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("smoke")
class SafeParseTest {

    @Test
    void shouldParseValidNumbers() {
        assertEquals(-42, SafeParse.parseInt("-42"));
        assertEquals(12.5, SafeParse.parseDouble("12.5"));
    }

    @Test
    void shouldWrapInvalidIntegerWithOriginalCause() {
        NumberFormatException error = assertThrows(
                NumberFormatException.class,
                () -> SafeParse.parseInt("not-an-int")
        );

        assertEquals("Invalid integer: not-an-int", error.getMessage());
        assertInstanceOf(NumberFormatException.class, error.getCause());
    }

    @Test
    void shouldWrapInvalidDoubleWithOriginalCause() {
        NumberFormatException error = assertThrows(
                NumberFormatException.class,
                () -> SafeParse.parseDouble("not-a-double")
        );

        assertEquals("Invalid number: not-a-double", error.getMessage());
        assertInstanceOf(NumberFormatException.class, error.getCause());
    }
}
