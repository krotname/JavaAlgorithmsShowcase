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
import static kyu6.EncryptThis.*;
@Tag("smoke")
public class EncryptThisTest {
    @Test
    void encryptsWordsSeparatedByWhitespace() {
        assertEquals("65 66", encryptThis("A  B"));
        assertEquals("65 66", encryptThis(" A\tB "));
    }

    @Test
    void returnsEmptyStringForBlankInput() {
        assertEquals("", encryptThis(" \t\n"));
    }

    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(kyu6.EncryptThis.class);
    }
}
