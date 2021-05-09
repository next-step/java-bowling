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
    public Score score() {
        return Score.spare();
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        if (beforeScore.isFinish()) {
            return beforeScore;
        }
        beforeScore = beforeScore.addBonusScore(firstPins.count());
        if (beforeScore.isFinish()) {
            return beforeScore;
        }
        return beforeScore.addBonusScore(secondPins.count());
    }

    @Override
    public String description() {
        return null;
    }
}
