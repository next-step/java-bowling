package bowling.domain.state.running;

import bowling.domain.pin.PinCount;
import bowling.domain.pin.Pins;
import bowling.domain.state.State;
import bowling.domain.state.StateExpression;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.finish.Spare;
import bowling.exception.CannotMatchStateException;

public class Hit extends Running {

    private final Pins firstPins;

    private Hit(final Pins firstPins) {
        if (firstPins.isStrike()) {
            throw new CannotMatchStateException(this.getClass().getName());
        }
        this.firstPins = firstPins;
    }

    public static Hit of(final Pins firstPins) {
        return new Hit(firstPins);
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
    public String getDesc() {
        return firstPins.getHitCount() + StateExpression.BLANK;
    }
}
