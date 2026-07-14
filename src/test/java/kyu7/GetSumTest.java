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
import static kyu7.GetSum.*;
@Tag("smoke")
public class GetSumTest {
    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(kyu7.GetSum.class);
    }

    @Test
    void shouldUseWideIntermediatesForBoundaryRanges() {
        assertEquals(Integer.MIN_VALUE, getSum(Integer.MIN_VALUE, Integer.MAX_VALUE));
        assertEquals(3, getSum(-2, 3));
        assertThrows(ArithmeticException.class, () -> getSum(Integer.MAX_VALUE - 1, Integer.MAX_VALUE));
    }
}
