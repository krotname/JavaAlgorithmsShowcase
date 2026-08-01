package algorithms.sprint2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit")
class DequeTest {

    @Test
    void rejectsCapacityInsteadOfSilentlyClampingIt() {
        assertThrows(IllegalArgumentException.class, () -> new Deque.RingDeque(100_001));
    }

    @Test
    void preservesSupportedCapacity() {
        Deque.RingDeque deque = new Deque.RingDeque(2);
        deque.pushBack(1);
        deque.pushBack(2);

        assertEquals(2, deque.popBack());
        assertEquals(1, deque.popBack());
    }
}
