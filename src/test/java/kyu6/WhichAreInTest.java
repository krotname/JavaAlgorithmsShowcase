package kyu6;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("smoke")
class WhichAreInTest {

    @Test
    void smokeTestsShouldExecuteApi() {
        quality.SmokeMethodTestHarness.verify(WhichAreIn.class);
    }

    @Test
    void shouldReturnSortedSubstringsFromTheKataExample() {
        String[] a1 = {"live", "arp", "strong"};
        String[] a2 = {"lively", "alive", "harp", "sharp", "armstrong"};

        assertArrayEquals(new String[]{"arp", "live", "strong"}, WhichAreIn.inArray(a1, a2));
    }

    @Test
    void shouldReturnEmptyArrayWhenNothingIsContained() {
        String[] a1 = {"tarp", "mice", "bull"};
        String[] a2 = {"lively", "alive", "harp", "sharp", "armstrong"};

        assertArrayEquals(new String[0], WhichAreIn.inArray(a1, a2));
    }

    @Test
    void shouldNotRepeatWordsThatOccurTwiceInTheFirstArray() {
        String[] a1 = {"arp", "live", "strong", "arp", "live"};
        String[] a2 = {"lively", "alive", "harp", "sharp", "armstrong"};

        assertArrayEquals(new String[]{"arp", "live", "strong"}, WhichAreIn.inArray(a1, a2));
    }
}
