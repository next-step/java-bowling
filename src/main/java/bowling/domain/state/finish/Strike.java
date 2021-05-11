package bowling.domain.state.finish;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;
import bowling.domain.state.State;
import bowling.exception.InvalidPinsSizeException;

public final class Strike extends Finish {

    private static final String STRIKE = "X";
    private final Pins pins;

    public static final State initialize() {
        return new Strike();
    }

    private Strike() {
        this.pins = Pins.full();
    }

    private final void validateStrike() {
        if (!pins.isStrike()) {
            throw new InvalidPinsSizeException();
        }
    }

    @Override
    public final boolean isAllPinClear() {
        return true;
    }

    @Override
    public final Score score() {
        return Score.strike();
    }

    @Override
    public final Score calculateAdditionalScore(final Score beforeScore) {
        if (beforeScore.isFinish()) {
            return beforeScore;
        }
        return beforeScore.addBonusScore(pins.count());
    }

    @Override
    public final String description() {
        validateStrike();
        return STRIKE;
    }

}
