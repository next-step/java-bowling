package bowling.domain.state.finish;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;
import bowling.exception.InvalidSpareSizeException;
import bowling.exception.PinsNullPointerException;

import java.util.Objects;

public final class Spare extends Finish {

    private static final String DESCRIPTION = "%s|%s";
    private static final String SPARE = "/";
    private static final String GUTTER = "-";

    private final Pins firstPins;
    private final Pins secondPins;

    public Spare(final int firstPins, final int secondPins) {
        this(Pins.valueOf(firstPins), Pins.valueOf(secondPins));
    }

    public Spare(final Pins firstPins, final Pins secondPins) {
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
        Score addedScore = beforeScore.addBonusScore(firstCount());
        if (addedScore.isFinish()) {
            return addedScore;
        }
        return addedScore.addBonusScore(secondCount());
    }

    private final int firstCount() {
        return firstPins.count();
    }

    private final int secondCount() {
        return secondPins.count();
    }

    @Override
    public final String description() {
        validateSize(firstPins, secondPins);
        return String.format(DESCRIPTION, toMark(firstPins), SPARE);
    }

    private final String toMark(final Pins pins) {
        if (pins.isEmpty()) {
            return GUTTER;
        }
        return String.valueOf(pins.count());
    }

}
