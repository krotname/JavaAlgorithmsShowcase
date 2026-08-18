package algorithms.sprint1;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DistancesTest {

    @Test
    void readsAndWritesThroughProvidedStreams() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        Distances.run(input("5 0 1 4 9 0"), output);

        assertEquals("0 1 2 1 0\n", output.toString(StandardCharsets.UTF_8));
    }

    @Test
    void rejectsNegativeInputSize() {
        assertThrows(IllegalArgumentException.class,
                () -> Distances.run(input("-1"), new ByteArrayOutputStream()));
    }

    @Test
    void rejectsExcessiveInputSize() {
        assertThrows(IllegalArgumentException.class,
                () -> Distances.run(input("100001"), new ByteArrayOutputStream()));
    }

    private static ByteArrayInputStream input(String value) {
        return new ByteArrayInputStream(value.getBytes(StandardCharsets.UTF_8));
    }
}
