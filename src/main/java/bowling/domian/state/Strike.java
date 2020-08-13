package bowling.domian.state;

import bowling.domian.state.exception.BowlFinishedException;

public class Strike implements State {
    private static final String BOWL_FINISHED_EXCEPTION_MSG = "Strike 후에는 투구가 불가능 합니다!";

    @Override
    public State bowl(int falledPinsCount) {
        throw new BowlFinishedException(BOWL_FINISHED_EXCEPTION_MSG);
    }

    public boolean isFinished() {
        return true;
    }
}
