package bowling.domain.state.running;

import bowling.domain.pin.PinCount;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.finish.Spare;
import bowling.exception.CannotMatchStateException;

public class FirstHit extends Running {

    private final Pins firstPins;

    private FirstHit(final Pins firstPins) {
        if (firstPins.isStrike()) {
            throw new CannotMatchStateException(this.getClass().getName());
        }
        this.firstPins = firstPins;
    }

    public static FirstHit of(final Pins firstPins) {
        return new FirstHit(firstPins);
    }

    @Override
    public State bowl(final PinCount hitCount) {
        Pins pins = Pins.of(hitCount);

        if (firstPins.isSpare(pins)) {
            return Spare.of(firstPins, pins);
        }
        return Miss.of(firstPins, pins);
    }

    @Override
    public Pins getFirstPins() {
        return this.firstPins;
    }

    @Override
    public Score getScore() {
        return Score.valueOf(this.firstPins.getHitCount(), Score.ZERO);
    }

    @Override
    public Score calculateScoreForExtraBonusCount(Score beforeScore) {
        if (beforeScore.isZeroOfExtraBonusCount()) {
            return beforeScore;
        }
        beforeScore = this.firstPins.sumScore(beforeScore);
        if (beforeScore.isZeroOfExtraBonusCount()) {
            return beforeScore;
        }

        return Score.INIT_SCORE;
    }
}
