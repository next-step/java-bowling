package bowling.domain.state.finish;

import bowling.domain.pin.PinCount;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.exception.CannotMatchStateException;

public class Spare extends Finished {

    private final Pins firstPins;

    private Spare(final Pins firstPins, final Pins secondPins) {
        if (!firstPins.isSpare(secondPins)) {
            throw new CannotMatchStateException(this.getClass().getName());
        }
        this.firstPins = firstPins;
    }

    public static Spare of(final Pins firstPins, final Pins secondPins) {
        return new Spare(firstPins, secondPins);
    }

    @Override
    public Pins getFirstPins() {
        return this.firstPins;
    }

    public Pins getSecondPins() {
        return Pins.of(PinCount.MAX_COUNT - this.firstPins.getHitCount());
    }

    @Override
    public Score getScore() {
        return Score.ofSpare();
    }

    @Override
    protected Score calculateScoreForExtraBonusCount(Score beforeScore) {
        beforeScore = this.firstPins.sumScore(beforeScore);
        if (beforeScore.isCalculable()) {
            return beforeScore;
        }

        return this.getSecondPins().sumScore(beforeScore);
    }
}
