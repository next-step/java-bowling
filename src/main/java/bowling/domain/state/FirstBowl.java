package bowling.domain.state;

import bowling.domain.value.Pins;

public class FirstBowl implements State {
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
    public boolean isFinish() {
        return false;
    }

    @Override
    public String mark() {
        return String.valueOf(firstPins.getPins());
    }
}
