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
import static kyu6.DigPow.*;
@Tag("smoke")
public class DigPowTest {
    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(kyu6.DigPow.class);
    }

    @Test
    void shouldMatchExamplesAndRejectInvalidOrOverflowingInputs() {
        assertEquals(1, digPow(89, 1));
        assertEquals(-1, digPow(92, 1));
        assertEquals(2, digPow(695, 2));
        assertEquals(51, digPow(46288, 3));
        assertThrows(IllegalArgumentException.class, () -> digPow(0, 1));
        assertThrows(IllegalArgumentException.class, () -> digPow(89, 0));
        assertThrows(ArithmeticException.class, () -> digPow(9, Integer.MAX_VALUE));
    }
}
