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
import static kyu5.NumberOfTrailingZerosOfN.*;
@Tag("smoke")
public class NumberOfTrailingZerosOfNTest {
    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(kyu5.NumberOfTrailingZerosOfN.class);
    }

    @Test
    void shouldCountFactorsOfFiveWithoutBuildingTheFactorial() {
        assertEquals(0, zeros(0));
        assertEquals(1, zeros(5));
        assertEquals(6, zeros(25));
        assertTimeoutPreemptively(java.time.Duration.ofSeconds(1), () -> assertEquals(24, zeros(100)));
    }

    @Test
    void shouldRejectNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> zeros(-1));
    }
}
