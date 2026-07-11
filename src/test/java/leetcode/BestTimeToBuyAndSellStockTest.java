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
import static leetcode.BestTimeToBuyAndSellStock.*;
@Tag("smoke")
public class BestTimeToBuyAndSellStockTest {
    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(leetcode.BestTimeToBuyAndSellStock.class);
    }

    @Test
    void shouldTrackTheLowestEarlierPriceInOnePass() {
        assertEquals(5, maxProfit1(new int[]{7, 1, 5, 3, 6, 4}));
        assertEquals(0, maxProfit1(new int[]{7, 6, 4, 3, 1}));
        assertThrows(IllegalArgumentException.class, () -> maxProfit1(null));
        assertThrows(IllegalArgumentException.class, () -> maxProfit1(new int[]{1, -1, 2}));
    }
}
