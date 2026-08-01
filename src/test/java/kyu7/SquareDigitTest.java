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
import static kyu7.SquareDigit.*;
@Tag("smoke")
public class SquareDigitTest {
    @Test
    void squaresDigitsWhenResultFitsInAnInteger() {
        assertEquals(811181, squareDigits(9119));
    }

    @Test
    void capsResultsThatExceedIntegerRange() {
        assertEquals(Integer.MAX_VALUE, squareDigits(99999));
    }

    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(kyu7.SquareDigit.class);
    }
}
