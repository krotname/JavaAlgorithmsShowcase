package algorithms.sprint0;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit")
class ZipTest {

    @Test
    void interleavesEqualLengthLists() {
        List<Integer> a = Arrays.asList(1, 3, 5);
        List<Integer> b = Arrays.asList(2, 4, 6);

        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), Zip.zip(a, b, 3));
    }

    @Test
    void clampsByRequestedLengthAndActualListSizes() {
        assertEquals(Arrays.asList(10, 20, 30, 40),
                Zip.zip(Arrays.asList(10, 30, 50), Arrays.asList(20, 40, 60), 2));
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6),
                Zip.zip(Arrays.asList(1, 3, 5), Arrays.asList(2, 4, 6), 10));
        assertEquals(Arrays.asList(1, 2), Zip.zip(List.of(1), Arrays.asList(2, 4, 6), 3));
    }

    @Test
    void returnsEmptyListForZeroOrEmptyInputs() {
        assertTrue(Zip.zip(Arrays.asList(1, 3, 5), Arrays.asList(2, 4, 6), 0).isEmpty());
        assertTrue(Zip.zip(List.of(), List.of(), 5).isEmpty());
    }

    @Test
    void rejectsNegativeLength() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> Zip.zip(List.of(1), List.of(2), -1));

        assertTrue(exception.getMessage().contains("n >= 0"));
    }

    @Test
    void rejectsNullLists() {
        assertThrows(NullPointerException.class, () -> Zip.zip(null, List.of(2), 1));
        assertThrows(NullPointerException.class, () -> Zip.zip(List.of(1), null, 1));
    }

    @Test
    void processAcceptsValidInput() throws IOException {
        StringWriter output = new StringWriter();
        BufferedWriter writer = new BufferedWriter(output);

        Zip.process(new BufferedReader(new StringReader("3\n1 5 6\n7 8 9\n")), writer);
        writer.flush();

        assertEquals("1 7 5 8 6 9 ", output.toString());
    }

    @Test
    void processRejectsMissingOrShortLists() {
        assertThrows(IOException.class, () -> process("3\n1 2 3\n"));
        assertThrows(IllegalArgumentException.class, () -> process("3\n1 2\n4 5 6\n"));
    }

    @Test
    void processRejectsUnboundedSizesAndLines() {
        assertThrows(IllegalArgumentException.class, () -> process("100001\n1\n2\n"));
        String oversizedLine = "1".repeat(1_200_002);
        assertThrows(IllegalArgumentException.class, () -> process("1\n" + oversizedLine + "\n2\n"));
    }

    private static void process(String input) throws IOException {
        Zip.process(new BufferedReader(new StringReader(input)), new BufferedWriter(new StringWriter()));
    }
}
