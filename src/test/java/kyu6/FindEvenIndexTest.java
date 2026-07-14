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
import static kyu6.FindEvenIndex.*;
@Tag("smoke")
public class FindEvenIndexTest {
    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(kyu6.FindEvenIndex.class);
    }

    @Test
    void implementationsShouldHandleNegativeSumsAndOverflow() {
        int[][] inputs = {
                {20, 10, -80, 10, 10, 15, 35},
                {1, 2, 3, 4, 3, 2, 1},
                {Integer.MAX_VALUE, 0, Integer.MAX_VALUE}
        };
        int[] expected = {0, 3, 1};

        for (int i = 0; i < inputs.length; i++) {
            assertEquals(expected[i], findEvenIndexV1(inputs[i]));
            assertEquals(expected[i], findEvenIndexV2(inputs[i]));
        }
    }
}
