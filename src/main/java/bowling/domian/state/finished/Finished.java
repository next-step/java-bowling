package bowling.domian.state.finished;

import bowling.domian.state.State;
import bowling.domian.state.exception.BowlFinishedException;

public abstract class Finished implements State {
    private static final String BOWL_FINISHED_EXCEPTION_MSG = "투구가 끝난 후에는 추가 투구가 불가능 합니다!";

    @Override
    public State bowl(int falledPinsCount) {
        throw new BowlFinishedException(BOWL_FINISHED_EXCEPTION_MSG);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean canGetScore() {
        return true;
    }
}
