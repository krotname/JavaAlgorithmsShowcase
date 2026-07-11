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
import static other.SpinWords.*;
@Tag("smoke")
public class SpinWordsTest {
    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(other.SpinWords.class);
    }

    @Test
    void shouldReverseLongWordsAndPreserveWhitespace() {
        other.SpinWords spinWords = new other.SpinWords();

        assertEquals("Hey wollef sroirraw", spinWords.spinWords("Hey fellow warriors"));
        assertEquals("  emocleW\tto  the elgnuj ", spinWords.spinWords("  Welcome\tto  the jungle "));
        assertThrows(IllegalArgumentException.class, () -> spinWords.spinWords(null));
    }
}
