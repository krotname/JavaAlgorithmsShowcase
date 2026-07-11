import org.junit.jupiter.api.Tag;
import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import net.jqwik.api.Property;
import net.jqwik.api.ForAll;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static kyu7.ReverseWords.*;
@Tag("smoke")
public class ReverseWordsTest {
    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(kyu7.ReverseWords.class);
    }

    @Test
    void shouldPreserveLeadingTrailingAndRepeatedWhitespace() {
        assertEquals("  cba\t fed  ", reverseWords("  abc\t def  "));
        assertEquals("", reverseWords(""));
    }

    @Test
    void shouldRejectNullInput() {
        assertThrows(IllegalArgumentException.class, () -> reverseWords(null));
    }
}
