package bowling.domain.state;

import bowling.domain.value.Pins;
import bowling.utils.Preconditions;

public class Spare implements State {
    private static final String SPARE = "/";

    private final Pins firstPins;
    private final Pins secondPins;

    private Spare(Pins firstPins, Pins secondPins) {
        Preconditions.checkState(firstPins.isSpare(secondPins), "투구의 합이 10이 되어야 합니다.");

        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static State of(Pins firstPins, Pins secondPin) {


        return new Spare(firstPins, secondPin);
    }

    @Override
    public State pitch(Pins pins) {
        throw new IllegalArgumentException(INVALID_PITCH);
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public String mark() {
        return checkGutter(firstPins) + DELIMITER + SPARE;
    }
}
