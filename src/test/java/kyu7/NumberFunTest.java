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
import static kyu7.NumberFun.*;
@Tag("smoke")
public class NumberFunTest {
    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(kyu7.NumberFun.class);
    }

    @Test
    void shouldHandleLongBoundariesWithoutFloatingPointMisclassification() {
        assertEquals(1, findNextSquare(0));
        assertEquals(9_223_372_030_926_249_001L, findNextSquare(9_223_372_024_852_248_004L));
        assertEquals(-1, findNextSquare(Long.MAX_VALUE));
        assertThrows(ArithmeticException.class, () -> findNextSquare(9_223_372_030_926_249_001L));
    }
}
