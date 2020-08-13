package bowling.domian.state;

import bowling.domian.state.exception.BowlFinishedException;

public class Strike implements State {
    private static final String BOWL_FINISHED_EXCEPTION_MSG = "스트라이크 후에는 투구 불가능!";

    @Override
    public State bowl(int falledPinsCount) {
        throw new BowlFinishedException(BOWL_FINISHED_EXCEPTION_MSG);
    }

    public boolean isFinished() {
        return true;
    }
}
