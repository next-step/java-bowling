package bowling.domian.state;

import bowling.domian.state.exception.BowlFinishedException;

public class Miss implements State {
    private static final String BOWL_FINISHED_EXCEPTION_MSG = "두 번의 투구가 모두 끝났습니다!";

    @Override
    public State bowl(int falledPinsCount) {
        throw new BowlFinishedException(BOWL_FINISHED_EXCEPTION_MSG);
    }

    public boolean isFinished() {
        return true;
    }
}
