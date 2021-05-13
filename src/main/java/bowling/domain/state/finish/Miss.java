package bowling.domain.state.finish;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;
import bowling.exception.InvalidMissSizeException;
import bowling.exception.PinsNullPointerException;

import java.util.Objects;

import static java.lang.Math.addExact;

public final class Miss extends Finish {

    private static final String DESCRIPTION = "%s|%s";
    private static final String GUTTER = "-";

    private final Pins firstPins;
    private final Pins secondPins;

    public Miss(final int firstPins, final int secondPins) {
        this(Pins.valueOf(firstPins), Pins.valueOf(secondPins));
    }

    public Miss(final Pins firstPins, final Pins secondPins) {
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
        return addExact(firstCount(), secondCount());
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
        return String.format(DESCRIPTION, toMark(firstPins), toMark(secondPins));
    }

    private final String toMark(final Pins pins) {
        if (pins.isEmpty()) {
            return GUTTER;
        }
        return String.valueOf(pins.count());
    }

}
