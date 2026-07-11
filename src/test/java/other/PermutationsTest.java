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
import static other.Permutations.*;
@Tag("smoke")
public class PermutationsTest {
    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(other.Permutations.class);
    }

    @Test
    void shouldResetResultsBetweenTopLevelInvocations() {
        other.Permutations permutations = new other.Permutations();
        permutations.permutations(new int[]{1, 2}, 0);
        assertEquals(2, permutations.result().size());

        permutations.permutations(new int[]{3}, 0);
        assertEquals(1, permutations.result().size());
        assertArrayEquals(new int[]{33}, permutations.result().get(0));
        assertThrows(IllegalArgumentException.class, () -> permutations.permutations(new int[]{1}, 1));
    }
}
