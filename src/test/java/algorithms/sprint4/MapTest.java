package algorithms.sprint4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.OptionalInt;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Tag("unit")
class MapTest {

    @Test
    void hashTableSupportsPutGetUpdateAndDelete() {
        Map.HashTable table = new Map.HashTable();

        table.put(1, 10);
        table.put(1, 20);

        assertEquals(OptionalInt.of(20), table.get(1));
        assertEquals(OptionalInt.of(20), table.delete(1));
        assertTrue(table.get(1).isEmpty());
        assertTrue(table.delete(1).isEmpty());
    }

    @Test
    @Timeout(10)
    void readerReportsEndOfInputInsteadOfReplayingTheBuffer() throws IOException {
        InputStream original = System.in;
        try {
            System.setIn(new ByteArrayInputStream("g 7".getBytes(StandardCharsets.UTF_8)));
            Map.Reader reader = new Map.Reader();

            assertEquals('g', reader.nextCommand());
            assertEquals(7, reader.nextInt());
            assertThrows(IOException.class, reader::nextCommand);
            assertThrows(IOException.class, reader::nextInt);
        } finally {
            System.setIn(original);
        }
    }

    @Test
    void hashTableHandlesCollisionsAndNegativeKeys() {
        Map.HashTable table = new Map.HashTable();

        table.put(5, 50);
        table.put(100_008, 60);
        table.put(-5, 70);

        assertEquals(OptionalInt.of(50), table.get(5));
        assertEquals(OptionalInt.of(60), table.get(100_008));
        assertEquals(OptionalInt.of(70), table.get(-5));
    }
}
