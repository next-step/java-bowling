package bowling.domain.rolling;

import bowling.domain.state.StateFormat;

public class Rolling {
    private static final int PIN_COUNT_MAX = 10;

    private final StateFormat stateFormat;
    private final int knockedDownPinCount;

    Rolling(StateFormat stateFormat, int knockedDownPinCount) {
        this.stateFormat = stateFormat;
        this.knockedDownPinCount = knockedDownPinCount;
    }

    StateFormat getStateFormat() {
        return stateFormat;
    }

    boolean isState(StateFormat stateFormat) {
        return stateFormat == stateFormat;
    }

    int getRemainPinCount() {
        return PIN_COUNT_MAX - knockedDownPinCount;
    }

    int getKnockedDownPinCount() {
        return knockedDownPinCount;
    }

    String getStateFormatValue() {
        return stateFormat.getState(knockedDownPinCount);
    }
}
