package camp.nextstep.edu.rebellion.bowling.domain.frame;

import java.util.concurrent.atomic.AtomicInteger;

public class Attempt {
    private static final int FIRST_ATTEMPT = 0;
    private static final int MAX_ATTEMPT_COUNTS = 2;

    private AtomicInteger numberOfAttempts;

    public Attempt(int initAttempt) {
        checkRange(initAttempt);
        numberOfAttempts = new AtomicInteger(initAttempt);
    }

    public void tried() {
        this.numberOfAttempts.incrementAndGet();
    }

    public boolean isFirstAttempt() {
        return FIRST_ATTEMPT == this.numberOfAttempts.get();

    }

    public boolean hasAttempt() {
        return this.numberOfAttempts.get() < MAX_ATTEMPT_COUNTS;
    }


    private void checkRange(int initAttempt) {
        if (initAttempt < FIRST_ATTEMPT || initAttempt > MAX_ATTEMPT_COUNTS) {
            throw new IllegalArgumentException("시도 회수 초기 값이 잘못 되었습니다 (최대 2) : "
                    + initAttempt);
        }
    }
}
