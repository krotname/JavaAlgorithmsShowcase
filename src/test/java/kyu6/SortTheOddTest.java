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
import static kyu6.SortTheOdd.*;
@Tag("smoke")
public class SortTheOddTest {
    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(kyu6.SortTheOdd.class);
    }

    @Test
    void shouldSortOddValuesAndLeaveEvenValuesInPlace() {
        int[] input = {5, 3, 2, 8, 1, 4};
        int[] expected = {1, 3, 2, 8, 5, 4};

        assertArrayEquals(expected, sortArray(input));
        assertArrayEquals(new int[]{5, 3, 2, 8, 1, 4}, input);
        assertArrayEquals(expected, sortArrayStream(input));
    }

    @Test
    void shouldRecognizeNegativeOddValues() {
        assertArrayEquals(new int[]{-5, 2, -3}, sortArray(new int[]{-3, 2, -5}));
    }
}
