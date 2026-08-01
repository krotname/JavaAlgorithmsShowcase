package coderun;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("smoke")
class AverageElementTest {

    @Test
    void shouldReturnMiddleValueAfterSortingThreeNumbers() {
        assertEquals(5, AverageElement.average(new String[]{"10", "5", "1"}));
    }

    @Test
    void shouldHandleNegativeValues() {
        assertEquals(-2, AverageElement.average(new String[]{"-10", "-2", "7"}));
    }

    @Test
    void shouldSolveFromReaderAndNormalizeWhitespace() throws IOException {
        StringWriter output = new StringWriter();

        AverageElement.solve(new StringReader("10   5  1"), output);

        assertEquals("5", output.toString());
    }

    @Test
    void shouldIgnoreInvalidOrInsufficientInput() throws IOException {
        assertProducesNoOutput("");
        assertProducesNoOutput("   ");
        assertProducesNoOutput("5");
        assertProducesNoOutput("1 invalid 3");
    }

    @Test
    void shouldRejectAnOversizedInputLine() throws IOException {
        assertProducesNoOutput("1 ".repeat(2049));
    }

    @Test
    void shouldFindSecondValueWithoutSortingTheInput() {
        assertEquals(2, AverageElement.average(new String[]{"4", "2", "3", "1"}));
        assertEquals(1, AverageElement.average(new String[]{"1", "1"}));
    }

    private static void assertProducesNoOutput(String input) throws IOException {
        StringWriter output = new StringWriter();

        AverageElement.solve(new StringReader(input), output);

        assertEquals("", output.toString());
    }
}
