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
import static kyu7.NbYear.*;
@Tag("smoke")
public class NbYearTest {
    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(kyu7.NbYear.class);
    }

    @Test
    void shouldCalculateGrowthAndRejectAnUnreachableTarget() {
        assertEquals(15, nbYear(1_500, 5.0, 100, 5_000));
        assertThrows(IllegalArgumentException.class, () -> nbYear(1_000, 0.0, 0, 1_001));
        assertThrows(IllegalArgumentException.class, () -> nbYear(1_000, Double.NaN, 1, 1_001));
    }
}
