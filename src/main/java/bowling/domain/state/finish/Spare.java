package bowling.domain.state.finish;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;
import bowling.domain.state.State;
import bowling.exception.InvalidSpareSizeException;
import bowling.exception.PinsNullPointerException;

import java.util.Objects;

public class Spare extends Finish {

    private final Pins firstPins;
    private final Pins secondPins;

    public static final State of(final Pins firstPins, final Pins secondPins) {
        return new Spare(firstPins, secondPins);
    }

    private Spare(final Pins firstPins, final Pins secondPins) {
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
        if (!firstPins.isSpare(secondPins.count())) {
            throw new InvalidSpareSizeException();
        }
    }

    @Override
    public final boolean isAllPinClear() {
        return true;
    }

    @Override
    public final Score score() {
        return Score.spare();
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
