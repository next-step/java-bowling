package bowling_step3.domain;

public enum State {
    INIT(-1), DONE(0), WAIT_TWICE(2), WAIT_ONCE(1);
    private final int remainingWait;

    State(int remainingWait) {
        this.remainingWait = remainingWait;
    }

    public static State of(int remainingWait) {
        if (INIT.remainingWait == remainingWait) {
            return INIT;
        }
        if (DONE.remainingWait == remainingWait) {
            return DONE;
        }
        if (WAIT_TWICE.remainingWait == remainingWait) {
            return WAIT_TWICE;
        }
        if (WAIT_ONCE.remainingWait == remainingWait) {
            return WAIT_ONCE;
        }
        throw new UnsupportedOperationException("Unreachable");
    }

    public State decreaseWait() {
        return of(this.remainingWait - 1);
    }

    public boolean waiting() {
        return this.remainingWait > 0;
    }
}
