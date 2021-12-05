package bowling.domain.state;

import bowling.domain.value.Pins;

public class FirstBowl extends InprogressState {
    private final Pins firstPins;

    private FirstBowl(Pins firstPins) {
        this.firstPins = firstPins;
    }

    public static State of(Pins pins) {
        return new FirstBowl(pins);
    }

    @Override
    public State pitch(Pins secondPins) {
        if (firstPins.isSpare(secondPins)) {
            return Spare.of(firstPins, secondPins);
        }

        if (secondPins.isGutter()) {
            return Gutter.of(firstPins, secondPins);
        }

        return Miss.of(firstPins, secondPins);
    }

    @Override
    public int countPins() {
        return firstPins.getPins();
    }

    @Override
    public String getMark() {
        return checkGutter(firstPins);
    }
}
