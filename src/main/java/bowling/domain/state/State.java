package bowling.domain.state;

import bowling.domain.pin.PinCount;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

import java.util.List;

public abstract class State {

    public abstract State bowl(PinCount hitCount);

    public boolean isFinish() {
        return false;
    }

    public boolean isMiss() {
        return false;
    }

    public boolean isCleanState() {
        return false;
    }

    public abstract Pins getFirstPins();

    public abstract Pins getSecondPins();

    public abstract List<State> getState();

    public abstract Score getScore();

    public Score calculateBonusScore(Score beforeScore) {
        if (beforeScore.isCalculable()) {
            return beforeScore;
        }

        return calculateScoreForExtraBonusCount(beforeScore);
    }

    public abstract Score calculateScoreForExtraBonusCount(Score beforeScore);
}
