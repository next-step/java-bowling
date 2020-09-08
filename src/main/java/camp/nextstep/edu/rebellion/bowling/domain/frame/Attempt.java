package camp.nextstep.edu.rebellion.bowling.domain.frame;

import java.util.concurrent.atomic.AtomicInteger;

public class Attempt {
    private static final int NO_ATTEMPT = 0;
    private static final int MAX_ATTEMPT = 3;

    private final int maxAttemptCount;
    private final AtomicInteger numberOfAttempts;

    public Attempt(int maxAttemptCount) {
        checkRange(maxAttemptCount);
        this.maxAttemptCount = maxAttemptCount;
        this.numberOfAttempts = new AtomicInteger(maxAttemptCount);
    }

    public static Attempt reset(int maxAttemptCount) {
        return new Attempt(maxAttemptCount);
    }

    public void tried() {
        this.numberOfAttempts.decrementAndGet();
    }

    public boolean isFirstAttempt() {
        return maxAttemptCount == this.numberOfAttempts.get();
    }

    public boolean hasAttempt() {
        return this.numberOfAttempts.get() > NO_ATTEMPT;
    }

    public void setNoAttempt() {
        this.numberOfAttempts.set(NO_ATTEMPT);
    }

    private void checkRange(int maxAttemptCount) {
        if (NO_ATTEMPT > maxAttemptCount || MAX_ATTEMPT < maxAttemptCount) {
            throw new IllegalArgumentException("시도 횟 수 초기 값이 잘못 되었습니다 (최대 3) : "
                    + maxAttemptCount);
        }
    }
}
