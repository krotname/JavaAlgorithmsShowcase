package algorithms.sprint7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit")
class LevenshteinDistanceTest {

    @Test
    void stripsOnlyTheOptionalCrLfTerminator() throws IOException {
        LevenshteinDistance.FastIn input = fastIn("abc\r\n");

        assertEquals("abc", input.nextLine(3));
    }

    @Test
    void countsEmbeddedCarriageReturnsTowardTheLimit() {
        LevenshteinDistance.FastIn input = fastIn("\r\r\r\n");

        assertThrows(IOException.class, () -> input.nextLine(1));
    }

    private static LevenshteinDistance.FastIn fastIn(String value) {
        return new LevenshteinDistance.FastIn(
                new ByteArrayInputStream(value.getBytes(StandardCharsets.UTF_8)));
    }
}
