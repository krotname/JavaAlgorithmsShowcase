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
import static kyu6.PhoneNumber.*;
@Tag("smoke")
public class PhoneNumberTest {
    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(kyu6.PhoneNumber.class);
    }

    @Test
    void shouldFormatAndValidateExactlyTenDigits() {
        assertEquals("(123) 456-7890", createPhoneNumber(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}));
        assertThrows(IllegalArgumentException.class, () -> createPhoneNumber(null));
        assertThrows(IllegalArgumentException.class, () -> createPhoneNumber(new int[9]));
        assertThrows(IllegalArgumentException.class,
                () -> createPhoneNumber(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }
}
