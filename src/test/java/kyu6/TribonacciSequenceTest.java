package kyu6;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("smoke")
class TribonacciSequenceTest {

    @Test
    void shouldReturnRequestedPrefixForSmallLengths() {
        double[] signature = {1, 1, 1};

        assertArrayEquals(new double[0], TribonacciSequence.tribonacci(signature, 0));
        assertArrayEquals(new double[]{1}, TribonacciSequence.tribonacci(signature, 1));
        assertArrayEquals(new double[]{1, 1}, TribonacciSequence.tribonacci(signature, 2));
    }

    @Test
    void shouldBuildSequenceFromSignature() {
        assertArrayEquals(
                new double[]{1, 1, 1, 3, 5, 9, 17, 31, 57, 105},
                TribonacciSequence.tribonacci(new double[]{1, 1, 1}, 10)
        );
    }

    @Test
    void shouldRejectInvalidSignatureAndLength() {
        assertThrows(IllegalArgumentException.class, () -> TribonacciSequence.tribonacci(null, 1));
        assertThrows(IllegalArgumentException.class, () -> TribonacciSequence.tribonacci(new double[]{1, 1}, 2));
        assertThrows(IllegalArgumentException.class, () -> TribonacciSequence.tribonacci(new double[]{1, 1, 1}, -1));
    }
}
