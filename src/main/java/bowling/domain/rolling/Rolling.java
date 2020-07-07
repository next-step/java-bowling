package bowling.domain.rolling;

public class Rolling {
    private static final int PIN_COUNT_MAX = 10;

    private final State state;
    private final int knockedDownPinCount;

    Rolling(State state, int knockedDownPinCount) {
        this.state = state;
        this.knockedDownPinCount = knockedDownPinCount;
    }

    State getState() {
        return state;
    }

    boolean isState(State state) {
        return this.state == state;
    }

    int getRemainPinCount() {
        return PIN_COUNT_MAX - knockedDownPinCount;
    }

    int getKnockedDownPinCount() {
        return knockedDownPinCount;
    }

    String getStateFormat() {
        return state.getState(knockedDownPinCount);
    }
}
