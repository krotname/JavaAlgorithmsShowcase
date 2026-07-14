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
import static kyu5.HumanReadableTime.*;
@Tag("smoke")
public class HumanReadableTimeTest {
    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(kyu5.HumanReadableTime.class);
    }

    @Test
    void shouldFormatBoundariesAndRejectOutOfRangeValues() {
        assertEquals("00:00:00", makeReadable(0));
        assertEquals("99:59:59", makeReadable(359_999));
        assertThrows(IllegalArgumentException.class, () -> makeReadable(-1));
        assertThrows(IllegalArgumentException.class, () -> makeReadable(360_000));
    }
}
