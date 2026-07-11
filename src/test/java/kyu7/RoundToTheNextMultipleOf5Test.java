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
import static kyu7.RoundToTheNextMultipleOf5.*;
@Tag("smoke")
public class RoundToTheNextMultipleOf5Test {
    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(kyu7.RoundToTheNextMultipleOf5.class);
    }

    @Test
    void shouldRoundNegativeValuesUpAndReportUnrepresentableResults() {
        assertEquals(0, roundToNext5(-1));
        assertEquals(-5, roundToNext5(-6));
        assertEquals(15, roundToNext5(12));
        assertThrows(ArithmeticException.class, () -> roundToNext5(Integer.MAX_VALUE));
    }
}
