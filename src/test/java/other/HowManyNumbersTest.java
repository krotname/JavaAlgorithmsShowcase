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
import static other.HowManyNumbers.*;
@Tag("smoke")
public class HowManyNumbersTest {
    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(other.HowManyNumbers.class);
    }

    @Test
    void shouldGenerateOnlyNonDecreasingDigitCombinations() {
        other.HowManyNumbers numbers = new other.HowManyNumbers();

        assertEquals(List.of(8L, 118L, 334L), numbers.findAll(10, 3));
        assertEquals(List.of(1L, 999L, 999L), numbers.findAll(27, 3));
        assertEquals(List.of(), numbers.findAll(84, 4));
        assertEquals(List.of(123L, 116999L, 566666L), numbers.findAll(35, 6));
        assertEquals(List.of(1L, 111111111111111111L, 111111111111111111L), numbers.findAll(18, 18));
        assertThrows(IllegalArgumentException.class, () -> numbers.findAll(10, 19));
    }
}
