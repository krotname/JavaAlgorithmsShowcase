package interview;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("smoke")
class CallbackSchedulerTest {

    @Test
    void shouldRunCallbacksScheduledInThePastPromptly() throws InterruptedException {
        CountDownLatch callbackExecuted = new CountDownLatch(1);

        try (CallbackScheduler scheduler = new CallbackScheduler()) {
            scheduler.schedule(callbackExecuted::countDown, Instant.now().minusMillis(10));

            assertTrue(callbackExecuted.await(500, TimeUnit.MILLISECONDS));
        }
    }

    @Test
    void shouldShutdownExecutorOnClose() {
        CallbackScheduler scheduler = new CallbackScheduler();

        scheduler.close();

        assertTrue(scheduler.isShutdown());
    }

    @Test
    void shouldOrderPreparedTasksByExecutionTime() {
        Runnable callback = () -> {
        };
        Instant now = Instant.now();

        CallbackScheduler.InstantRunnable earlier = CallbackScheduler.task(callback, now);
        CallbackScheduler.InstantRunnable later = CallbackScheduler.task(callback, now.plusSeconds(1));

        assertTrue(earlier.compareTo(later) < 0);
    }

    @Test
    void shouldSaturateDelayForTheMaximumInstant() {
        try (CallbackScheduler scheduler = new CallbackScheduler()) {
            assertDoesNotThrow(() -> scheduler.schedule(() -> {
            }, Instant.MAX));
        }
    }
}
