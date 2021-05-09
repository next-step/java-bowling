package bowling.domain.state;

import bowling.domain.frame.Frame;
import bowling.domain.score.Score;
import bowling.exception.InvalidFirstBowlSizeException;
import bowling.exception.PinsNullPointerException;

import java.util.Objects;

public final class FirstBowl extends Running {

    private static final int EMPTY_VALUE = 0;

    private final Pins firstBowl;

    public static final State from(Pins firstBowl) {
        return new FirstBowl(firstBowl);
    }

    private FirstBowl(final Pins firstBowl) {
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
    public Frame bowl(Pins pins) {
        return null;
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        return null;
    }

    @Override
    public String description() {
        return null;
    }
}
