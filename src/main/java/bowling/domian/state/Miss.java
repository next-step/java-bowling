package bowling.domian.state;

import bowling.domian.frame.Score;
import bowling.domian.state.exception.BowlFinishedException;

public class Miss implements State {
    private static final String BOWL_FINISHED_EXCEPTION_MSG = "두 번의 투구가 모두 끝났습니다!";

    private Pins first;
    private Pins second;

    public Miss(Pins first, Pins second) {
        this.first = first;
        this.second = second;
    }

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
        return false;
    }

    @Override
    public Score getScore() {
        return Score.miss(first.totalPinsCount(second));
    }

    @Override
    public Score calculateAdditional(Score lastScore) {
        lastScore = first.addScore(lastScore);

        if (!lastScore.isCalculateDone()) {
            lastScore = second.addScore(lastScore);
        }

        return lastScore;
    }
}
