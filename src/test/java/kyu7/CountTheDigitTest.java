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
import static kyu7.CountTheDigit.*;
@Tag("smoke")
public class CountTheDigitTest {
    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(kyu7.CountTheDigit.class);
    }

    @Test
    void shouldSquareUsingLongArithmetic() {
        assertEquals(54_139, nbDig(50_000, 2));
        assertThrows(IllegalArgumentException.class, () -> nbDig(-1, 2));
        assertThrows(IllegalArgumentException.class, () -> nbDig(10, 10));
    }
}
