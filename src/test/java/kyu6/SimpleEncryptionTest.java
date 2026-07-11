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
import static kyu6.SimpleEncryption.*;
@Tag("smoke")
public class SimpleEncryptionTest {
    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(kyu6.SimpleEncryption.class);
    }

    @Test
    void shouldRoundTripWithoutRecursiveStackGrowth() {
        String plainText = "This is a test!";
        String encrypted = encrypt(plainText, 10_000);

        assertEquals(plainText, decrypt(encrypted, 10_000));
        assertNull(encrypt(null, 3));
        assertEquals("", decrypt("", 3));
    }

    @Test
    void shouldMatchExamplesAndHandleMaximumIterationCountEfficiently() {
        assertEquals("hsi  etTi sats!", encrypt("This is a test!", 1));
        assertEquals("s eT ashi tist!", encrypt("This is a test!", 2));
        assertEquals("This is a test!", decrypt("hsi  etTi sats!", 1));
        assertEquals("This is a test!", decrypt("s eT ashi tist!", 2));

        String encrypted = encrypt("bounded permutation", Integer.MAX_VALUE);
        assertEquals("bounded permutation", decrypt(encrypted, Integer.MAX_VALUE));
    }
}
