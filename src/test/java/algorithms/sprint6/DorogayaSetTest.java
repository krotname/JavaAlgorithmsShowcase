package algorithms.sprint6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit")
class DorogayaSetTest {

    @Test
    void fastInRejectsOverflowingIntegerTokens() {
        assertThrows(NumberFormatException.class, () -> fastIn("4294967296 ").nextInt());
    }

    @Test
    void fastInAcceptsIntegerBounds() throws Exception {
        DorogayaSet.FastIn input = fastIn("-2147483648 2147483647 ");

        assertEquals(Integer.MIN_VALUE, input.nextInt());
        assertEquals(Integer.MAX_VALUE, input.nextInt());
    }

    private static DorogayaSet.FastIn fastIn(String value) {
        return new DorogayaSet.FastIn(
                new ByteArrayInputStream(value.getBytes(StandardCharsets.UTF_8)));
    }
}
