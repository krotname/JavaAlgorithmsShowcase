package kyu6;

import java.util.Locale;

import net.jqwik.api.ForAll;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Test;

import static kyu6.toCamelCase.toCamelCase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class toCamelCaseTest {

    @Test
    void shouldReturnSameWordWhenNoDelimiter() {
        assertEquals("simpleWord", toCamelCase("simpleWord"));
        assertEquals("AlreadyCamel", toCamelCase("AlreadyCamel"));
    }

    @Test
    void shouldConvertDashAndUnderscoreFormats() {
        assertEquals("theStealthWarrior", toCamelCase("the-stealth_warrior"));
        assertEquals("TheStealthWarrior", toCamelCase("The-Stealth-Warrior"));
    }

    @Test
    void shouldHandleEmptyInput() {
        assertEquals("", toCamelCase(""));
    }

    @Test
    void shouldFailOnEmptySegments() {
        assertThrows(IllegalArgumentException.class, () -> toCamelCase("--"));
        assertThrows(IllegalArgumentException.class, () -> toCamelCase("word-"));
        assertThrows(IllegalArgumentException.class, () -> toCamelCase("-word"));
        assertThrows(IllegalArgumentException.class, () -> toCamelCase("two--words"));
    }

    @Property
    @net.jqwik.api.Tag("property")
    void shouldHandleWordPairWithSingleSeparator(@ForAll @AlphaChars @StringLength(min = 1, max = 12) String head,
                                               @ForAll @AlphaChars @StringLength(min = 1, max = 12) String tail) {
        String input = head + "-" + tail;
        assertEquals(head + tail.substring(0, 1).toUpperCase(Locale.ROOT) + tail.substring(1), toCamelCase(input));
        assertNotNull(toCamelCase(input));
    }

    @Test
    void shouldHandleNullInputByReturningEmptyString() {
        assertEquals("", toCamelCase(null));
    }
}
