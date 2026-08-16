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
import static kyu6.Prime.*;
@Tag("smoke")
public class PrimeTest {
    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(kyu6.Prime.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 101, 7919, 2147483647})
    void shouldAcceptPrimes(int candidate) {
        assertTrue(isPrime(candidate));
    }

    @ParameterizedTest
    @ValueSource(ints = {-7, -1, 0, 1, 4, 9, 15, 25, 49, 121, 7917, 2147483645})
    void shouldRejectNonPrimes(int candidate) {
        assertFalse(isPrime(candidate));
    }
}
