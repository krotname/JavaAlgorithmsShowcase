package algorithms.sprint8;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit")
class PackedPrefixTest {

    @Test
    void rejectsEmptyRepeatBodies() {
        assertEquals("", PackedPrefix.solve(new String[]{"9[9[9[]]]"}));
    }

    @Test
    void rejectsZeroRepeatCounts() {
        assertEquals("", PackedPrefix.solve(new String[]{"0[a]"}));
    }
}
