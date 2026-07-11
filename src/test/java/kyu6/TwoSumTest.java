package kyu6;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static kyu6.TwoSum.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("smoke")
public class TwoSumTest {

    @ParameterizedTest
    @MethodSource("basicTests")
    void shouldFindMatchingPair(int[] input, int target, int[] expected) {
        assertArrayEquals(expected, twoSum(input, target));
    }

    static Stream<Arguments> basicTests() {
        return Stream.of(
                arguments(new int[]{2, 3, 1}, 4, new int[]{1, 2}),
                arguments(new int[]{1, 2, 3}, 4, new int[]{0, 2}),
                arguments(new int[]{1234, 5678, 9012}, 14690, new int[]{1, 2}),
                arguments(new int[]{2, 2, 3}, 444, new int[0])
        );
    }

    @Test
    void shouldReturnEmptyIfNoPair() {
        assertArrayEquals(new int[0], twoSum(new int[]{1, 2, 3}, 100));
        assertArrayEquals(
                new int[0],
                twoSum(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}, -2)
        );
    }

    @Test
    void shouldThrowOnNullInput() {
        assertThrows(NullPointerException.class, () -> twoSum(null, 0));
    }
}
