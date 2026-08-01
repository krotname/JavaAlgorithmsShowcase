package algorithms.sprint5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit")
class PyramidSortTest {

    @Test
    void fastInRejectsOverflowingInteger() {
        PyramidSort.FastIn in = fastIn("2147483648 ");

        assertThrows(NumberFormatException.class, in::nextInt);
    }

    @Test
    void fastInAcceptsMinimumInteger() throws Exception {
        assertEquals(Integer.MIN_VALUE, fastIn("-2147483648 ").nextInt());
    }

    @Test
    void fastInRejectsValuesBelowMinimumInteger() {
        assertThrows(NumberFormatException.class, () -> fastIn("-2147483649 ").nextInt());
    }

    @Test
    void fastInRejectsOversizedLoginToken() {
        PyramidSort.FastIn in = fastIn("a".repeat(1_025) + " ");

        assertThrows(java.io.IOException.class, in::next);
    }

    @Test
    void runRejectsNegativeParticipantCountBeforeAllocation() throws Exception {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("-1\n".getBytes(StandardCharsets.UTF_8)));
        try {
            Method run = PyramidSort.class.getDeclaredMethod("run");
            run.setAccessible(true);

            InvocationTargetException ex = assertThrows(InvocationTargetException.class, () -> run.invoke(null));

            assertEquals(IllegalArgumentException.class, ex.getCause().getClass());
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void solvePreservesParticipantOrdering() {
        PyramidSort.Participant[] participants = new PyramidSort.Participant[] {
                new PyramidSort.Participant("alla", 4, 100),
                new PyramidSort.Participant("gena", 6, 1000),
                new PyramidSort.Participant("timofey", 4, 80)
        };

        PyramidSort.solve(participants);

        assertEquals("gena", participants[0].login);
        assertEquals("timofey", participants[1].login);
        assertEquals("alla", participants[2].login);
    }

    private static PyramidSort.FastIn fastIn(String input) {
        return new PyramidSort.FastIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
    }
}
