package bowling.domain.state;

import bowling.domain.value.Pins;

public class FirstGutter extends InprogressState {
    private final Pins firstPins;

    private FirstGutter(Pins firstPins) {

        this.firstPins = firstPins;
    }

    public static State of(Pins firstPin) {
        return new FirstGutter(firstPin);
    }

    @Override
    public State pitch(Pins pins) {
        if (pins.isStrikeOrSpare()) {
            return Spare.of(firstPins, pins);
        }

        return Miss.of(firstPins, pins);
    }

    @Override
    public String mark() {
        return checkGutter(firstPins);
    }
}
