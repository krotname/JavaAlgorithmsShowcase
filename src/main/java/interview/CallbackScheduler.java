package interview;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.Builder;
import lombok.Value;


/**
 * Класс CallbackSchedulerImpl планирует исполнение Runnable в заданный момент времени.
 * Задачи делегируются ScheduledExecutorService, чтобы не держать отдельный ручной worker-loop.
 */
public class CallbackScheduler implements AutoCloseable {

    private final ScheduledExecutorService scheduler;

    public CallbackScheduler() {
        this(Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable, "callback-scheduler");
            thread.setDaemon(true);
            return thread;
        }));
    }

    CallbackScheduler(ScheduledExecutorService scheduler) {
        this.scheduler = Objects.requireNonNull(scheduler);
    }

    public void schedule(Runnable callback, Instant when) {
        Objects.requireNonNull(callback);
        Objects.requireNonNull(when);
        long delayMillis = delayMillisUntil(when);
        scheduler.schedule(callback, delayMillis, TimeUnit.MILLISECONDS);
    }

    private static long delayMillisUntil(Instant when) {
        Duration delay = Duration.between(Instant.now(), when);
        if (delay.isNegative() || delay.isZero()) {
            return 0L;
        }
        try {
            return delay.toMillis();
        } catch (ArithmeticException ignored) {
            // Instant supports a wider range than a millisecond delay stored in a long.
            return Long.MAX_VALUE;
        }
    }

    @Override
    public void close() {
        scheduler.shutdownNow();
    }

    public boolean isShutdown() {
        return scheduler.isShutdown();
    }

    public static InstantRunnable task(Runnable callback, Instant when) {
        return InstantRunnable.builder()
                .instant(Objects.requireNonNull(when))
                .runnable(Objects.requireNonNull(callback))
                .build();
    }

    /**
     * Value object retained for deterministic ordering tests and for callers that need
     * to prepare scheduled work before submitting it.
     */
    @Value
    @Builder
    public static class InstantRunnable implements Comparable<InstantRunnable> {
        Instant instant;
        Runnable runnable;

        @Override
        public int compareTo(InstantRunnable other) {
            return instant.compareTo(other.instant);
        }
    }
}
