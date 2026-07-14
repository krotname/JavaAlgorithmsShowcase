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
import static kyu7.DontGiveMeFive.*;
@Tag("smoke")
public class DontGiveMeFiveTest {
    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(kyu7.DontGiveMeFive.class);
    }

    @Test
    void shouldCountWithoutIteratingAcrossTheWholeRange() {
        assertEquals(8, dontGiveMeFive(1, 9));
        assertEquals(12, dontGiveMeFive(4, 17));
        assertEquals(9, dontGiveMeFive(-5, 5));
        assertEquals(1, dontGiveMeFive(Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertThrows(IllegalArgumentException.class, () -> dontGiveMeFive(2, 1));
        assertEquals(1_680_985_958, dontGiveMeFive(Integer.MIN_VALUE, Integer.MAX_VALUE));

        for (int start = -100; start <= 100; start++) {
            int expected = 0;
            for (int end = start; end <= 100; end++) {
                if (!Integer.toString(end).contains("5")) {
                    expected++;
                }
                assertEquals(expected, dontGiveMeFive(start, end));
            }
        }
    }
}
