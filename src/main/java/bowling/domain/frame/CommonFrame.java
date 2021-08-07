package bowling.domain.frame;

import bowling.domain.state.State;

public class CommonFrame implements Frame {
    public static final int START_NUMBER = 1;
    public static final int END_NUMBER = 10;
    private static final int LIMIT_COUNT_OF_COMMON_FRAME = 9;

    private State state;

    private CommonFrame(State state) {
        this.state = state;
    }

    public static CommonFrame of() {
        return new CommonFrame(State.of());
    }

    @Override
    public boolean isBowlingFinish() {
        return false;
    }

}
