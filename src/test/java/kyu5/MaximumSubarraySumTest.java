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
import static kyu5.MaximumSubarraySum.*;
@Tag("smoke")
public class MaximumSubarraySumTest {
    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(kyu5.MaximumSubarraySum.class);
    }

    @Test
    void shouldFindMaximumContiguousSumWithoutEnumeratingSubarrays() {
        assertEquals(6, sequence(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        assertEquals(0, sequence(new int[]{-4, -2, -7}));
        assertEquals(0, sequence(new int[0]));
        assertThrows(ArithmeticException.class, () -> sequence(new int[]{Integer.MAX_VALUE, 1}));
    }
}
