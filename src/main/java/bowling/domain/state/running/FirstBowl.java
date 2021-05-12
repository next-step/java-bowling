package bowling.domain.state.running;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;
import bowling.domain.state.State;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.finish.Spare;
import bowling.exception.InvalidFirstBowlSizeException;
import bowling.exception.InvalidPinsSizeException;
import bowling.exception.PinsNullPointerException;

import java.util.Objects;

public final class FirstBowl extends Running {

    private static final String GUTTER = "-";
    private static final int EMPTY_VALUE = 0;

    private final Pins firstBowl;

    public FirstBowl(final int firstBowl) {
        this(Pins.valueOf(firstBowl));
    }

    public FirstBowl(final Pins firstBowl) {
        validateNull(firstBowl);
        validateSize(firstBowl);
        this.firstBowl = firstBowl;
    }

    private final void validateNull(final Pins firstBowl) {
        if (Objects.isNull(firstBowl)) {
            throw new PinsNullPointerException();
        }
    }

    private final void validateSize(final Pins firstBowl) {
        if (!firstBowl.isMiss(EMPTY_VALUE)) {
            throw new InvalidFirstBowlSizeException();
        }
    }

    @Override
    public final State bowl(final Pins secondBowl) {
        if (firstBowl.isSpare(secondBowl.count())) {
            return new Spare(firstBowl, secondBowl);
        }
        if (firstBowl.isMiss(secondBowl.count())) {
            return new Miss(firstBowl, secondBowl);
        }
        throw new InvalidPinsSizeException();
    }

    @Override
    public State bowl(final int pins) {
        return bowl(Pins.valueOf(pins));
    }

    @Override
    public final Score calculateAdditionalScore(final Score beforeScore) {
        if (beforeScore.isFinish()) {
            return beforeScore;
        }
        return beforeScore.addBonusScore(firstBowl.count());
    }

    @Override
    public final String description() {
        return toMark(firstBowl);
    }

    private final String toMark(final Pins pins) {
        if (pins.isEmpty()) {
            return GUTTER;
        }
        return String.valueOf(pins.count());
    }
}
