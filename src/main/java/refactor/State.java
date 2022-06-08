package refactor;

public enum State {
    WAITING(-1), DONE(0), WAIT_TWICE(2), WAIT_ONCE(1);
    private final int remaingWait;
    State(int remainingWait) {
        this.remaingWait = remainingWait;
    }
    public static State of(int remaingWait) {
        if (WAITING.remaingWait == remaingWait) {
            return WAITING;
        }
        if (DONE.remaingWait == remaingWait) {
            return DONE;
        }
        if (WAIT_TWICE.remaingWait == remaingWait) {
            return WAIT_TWICE;
        }
        if (WAIT_ONCE.remaingWait == remaingWait) {
            return WAIT_ONCE;
        }
        throw new UnsupportedOperationException("Unreachable");
    }

    public State decreaseWait() {
        return of(this.remaingWait - 1);
    }
}
