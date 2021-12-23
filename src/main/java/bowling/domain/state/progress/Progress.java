package bowling.domain.state.progress;

import bowling.domain.score.Score;
import bowling.domain.state.State;

public abstract class Progress implements State {

    @Override
    public Score score() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String symbol() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isMiss() {
        return false;
    }


    @Override
    public boolean isBonus() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
