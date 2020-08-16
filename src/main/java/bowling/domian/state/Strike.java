package bowling.domian.state;

import bowling.domian.frame.Score;
import bowling.domian.state.exception.BowlFinishedException;

public class Strike implements State {
    private static final String BOWL_FINISHED_EXCEPTION_MSG = "Strike 후에는 투구가 불가능 합니다!";

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

    @Override
    public Score getScore() {
        return Score.strike();
    }

    @Override
    public Score calculateAdditional(Score score) {
        score = score.additionalBowl(10);

        return score;
    }
}
