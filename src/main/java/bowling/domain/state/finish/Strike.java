package bowling.domain.state.finish;

import bowling.domain.score.Score;
import bowling.domain.state.State;

public final class Strike extends Finish {

    private static final int FULL = 10;

    public static final State initialize() {
        return new Strike();
    }

    private Strike() {
    }

    @Override
    public final boolean isAllPinClear() {
        return true;
    }

    @Override
    public final Score score() {
        return Score.strike();
    }

    @Override
    public final Score calculateAdditionalScore(Score beforeScore) {
        if (beforeScore.isFinish()) {
            return beforeScore;
        }
        return beforeScore.addBonusScore(FULL);
    }

    @Override
    public String description() {
        return null;
    }
}
