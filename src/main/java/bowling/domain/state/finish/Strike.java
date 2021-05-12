package bowling.domain.state.finish;

import bowling.domain.score.Score;

public final class Strike extends Finish {

    private static final String STRIKE = "X";

    public Strike() {
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
    public final Score calculateAdditionalScore(final Score beforeScore) {
        if (beforeScore.isFinish()) {
            return beforeScore;
        }
        return beforeScore.addBonusScore(score().score());
    }

    @Override
    public final String description() {
        return STRIKE;
    }

}
