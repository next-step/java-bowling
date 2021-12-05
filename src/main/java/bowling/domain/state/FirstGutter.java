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
    public State pitch(Pins secondPins) {
        if (secondPins.isStrikeOrSpare()) {
            return Spare.of(firstPins, secondPins);
        }

        if (secondPins.isGutter()) {
            return SecondGutter.of(firstPins, secondPins);
        }

        return Miss.of(firstPins, secondPins);
    }

    @Override
    public String mark() {
        return checkGutter(firstPins);
    }
}
