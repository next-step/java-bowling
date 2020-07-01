package bowling.domain.state.finish;

import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.exception.CannotMatchStateException;

public class Miss extends Finished {

    private final Pins firstPins;
    private final Pins secondPins;

    private Miss(final Pins firstPins, final Pins secondPins) {
        if (!firstPins.isMiss(secondPins)) {
            throw new CannotMatchStateException(this.getClass().getName());
        }
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static Miss of(final Pins firstPins, final Pins secondPins) {
        return new Miss(firstPins, secondPins);
    }

    @Override
    public boolean isMiss() {
        return true;
    }

    @Override
    public Pins getFirstPins() {
        return this.firstPins;
    }

    @Override
    public Pins getSecondPins() {
        return this.secondPins;
    }

    @Override
    public Score getScore() {
        return Score.ofMiss(firstPins.totalPins(secondPins).getCount());
    }

    @Override
    protected Score calculateScoreForExtraBonusCount(Score beforeScore) {
        beforeScore = this.firstPins.sumScore(beforeScore);
        if (beforeScore.isCalculable()) {
            return beforeScore;
        }
        beforeScore = this.secondPins.sumScore(beforeScore);

        return beforeScore;
    }
}
