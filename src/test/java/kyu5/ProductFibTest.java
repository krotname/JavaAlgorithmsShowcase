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
import static kyu5.ProductFib.*;
@Tag("smoke")
public class ProductFibTest {
    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(kyu5.ProductFib.class);
    }

    @Test
    void shouldFindExactAndNextFibonacciProducts() {
        assertArrayEquals(new long[]{0, 1, 1}, productFib(0));
        assertArrayEquals(new long[]{21, 34, 1}, productFib(714));
        assertArrayEquals(new long[]{34, 55, 0}, productFib(800));
        assertArrayEquals(new long[]{2_971_215_073L, 4_807_526_976L, 0}, productFib(Long.MAX_VALUE));
    }

    @Test
    void shouldCalculateFibonacciValuesExactlyWithinLongRange() {
        assertEquals(12_586_269_025L, getFibonacciValue(50));
        assertEquals(7_540_113_804_746_346_429L, getFibonacciValue(92));
        assertThrows(ArithmeticException.class, () -> getFibonacciValue(93));
        assertThrows(IllegalArgumentException.class, () -> productFib(-1));
    }
}
