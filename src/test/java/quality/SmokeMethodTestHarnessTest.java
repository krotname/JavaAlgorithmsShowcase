package quality;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SmokeMethodTestHarnessTest {

    @Test
    void rejectsClassesWhoseEveryExecutablePathFails() {
        assertThrows(AssertionError.class, () -> SmokeMethodTestHarness.verify(AlwaysFails.class));
    }

    @Test
    void acceptsAClassWhenAtLeastOneExecutablePathSucceeds() {
        assertDoesNotThrow(() -> SmokeMethodTestHarness.verify(PartiallyWorks.class));
    }

    private static final class AlwaysFails {
        static void fail(int ignored) {
            throw new IllegalStateException("expected failure");
        }
    }

    private static final class PartiallyWorks {
        static void fail(int ignored) {
            throw new IllegalStateException("expected failure");
        }

        static int succeed(int value) {
            return value;
        }
    }
}
