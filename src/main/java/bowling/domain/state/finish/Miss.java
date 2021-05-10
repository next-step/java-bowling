package bowling.domain.state.finish;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;
import bowling.domain.state.State;
import bowling.exception.InvalidMissSizeException;
import bowling.exception.PinsNullPointerException;

import java.util.Objects;

import static java.lang.Math.addExact;

public final class Miss extends Finish {

    private final Pins firstPins;
    private final Pins secondPins;

    public static final State of(final Pins firstPins, final Pins secondPins) {
        return new Miss(firstPins, secondPins);
    }

    private Miss(final Pins firstPins, final Pins secondPins) {
        validateNull(firstPins, secondPins);
        validateSize(firstPins, secondPins);
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    private final void validateNull(final Pins firstPins, final Pins secondPins) {
        if (Objects.isNull(firstPins) || Objects.isNull(secondPins)) {
            throw new PinsNullPointerException();
        }
    }

    private final void validateSize(final Pins firstPins, final Pins secondPins) {
        if (!firstPins.isMiss(secondPins.count())) {
            throw new InvalidMissSizeException();
        }
    }

    @Override
    public final boolean isAllPinClear() {
        return false;
    }

    @Override
    public final Score score() {
        return Score.miss(Pins.valueOf(sum()));
    }

    private final int sum() {
        return addExact(firstPins.count(), secondPins.count());
    }

    @Override
    public final Score calculateAdditionalScore(final Score beforeScore) {
        if (beforeScore.isFinish()) {
            return beforeScore;
        }
        Score addedScore = beforeScore.addBonusScore(firstPins.count());
        if (addedScore.isFinish()) {
            return addedScore;
        }
        return addedScore.addBonusScore(secondPins.count());
    }

    @Override
    public final String description() {
        return null;
    }
}
