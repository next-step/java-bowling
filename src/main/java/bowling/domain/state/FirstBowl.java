package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.exception.InvalidFirstBowlSizeException;
import bowling.exception.InvalidPinsSizeException;
import bowling.exception.PinsNullPointerException;

import java.util.Objects;

public final class FirstBowl extends Running {

    private static final int EMPTY_VALUE = 0;

    private final Pins fisrtBowl;

    public static final State from(Pins fallPins) {
        return new FirstBowl(fallPins);
    }

    private FirstBowl(final Pins fisrtBowl) {
        validateNull(fisrtBowl);
        validateSize(fisrtBowl);
        this.fisrtBowl = fisrtBowl;
    }

    private final void validateNull(final Pins fallPins) {
        if (Objects.isNull(fallPins)) {
            throw new PinsNullPointerException();
        }
    }

    private final void validateSize(final Pins fallPins) {
        if (!fallPins.isMiss(EMPTY_VALUE)) {
            throw new InvalidFirstBowlSizeException();
        }
    }

    @Override
    public final State bowl(final Pins secondBowl) {
        if(fisrtBowl.isSpare(secondBowl.count())){
            return Spare.of(fisrtBowl, secondBowl);
        }
        if(fisrtBowl.isMiss(secondBowl.count())) {
            return Miss.of(fisrtBowl, secondBowl);
        }
        throw new InvalidPinsSizeException();
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
