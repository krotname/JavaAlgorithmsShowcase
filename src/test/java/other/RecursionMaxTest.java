package other;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@Tag("smoke")
class RecursionMaxTest {

    @ParameterizedTest
    @MethodSource("maxCases")
    void shouldFindMaximumValue(List<Integer> values, int expected) {
        assertEquals(expected, RecursionMax.max(values));
        assertEquals(expected, RecursionMax.recursionMax(values));
    }

    @Test
    void shouldNotMutateInputList() {
        List<Integer> values = new ArrayList<>(List.of(3, 8, 1, 5));

        assertEquals(8, RecursionMax.recursionMax(values));
        assertEquals(List.of(3, 8, 1, 5), values);
    }

    @Test
    void shouldHandleLargeListsWithoutExhaustingTheStack() {
        List<Integer> values = Collections.nCopies(100_000, 42);

        assertEquals(42, RecursionMax.recursionMax(values));
    }

    private static Stream<Arguments> maxCases() {
        return Stream.of(
                Arguments.of(null, -1),
                Arguments.of(List.of(), -1),
                Arguments.of(List.of(5), 5),
                Arguments.of(List.of(2, 7), 7),
                Arguments.of(List.of(-10, -4, -7), -4),
                Arguments.of(List.of(1, 9, 3, 9), 9)
        );
    }
}
