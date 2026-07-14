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
import static kyu6.TheSupermarketQueue.*;
@Tag("smoke")
public class TheSupermarketQueueTest {
    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(kyu6.TheSupermarketQueue.class);
    }

    @Test
    void shouldQueueAllCustomersEvenWhenEveryServiceTimeIsShorterThanTillCount() {
        assertEquals(3, solveSuperMarketQueue(new int[]{1, 1, 1, 1, 1}, 2));
        assertEquals(9, solveSuperMarketQueue(new int[]{2, 2, 3, 3, 4, 4}, 2));
    }

    @Test
    void shouldRejectInvalidQueueConfiguration() {
        assertThrows(IllegalArgumentException.class, () -> solveSuperMarketQueue(null, 1));
        assertThrows(IllegalArgumentException.class, () -> solveSuperMarketQueue(new int[]{1}, 0));
        assertThrows(IllegalArgumentException.class, () -> solveSuperMarketQueue(new int[]{-1}, 1));
    }
}
