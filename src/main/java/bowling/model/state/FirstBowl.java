package bowling.model.state;

import bowling.model.Pins;

public class FirstBowl implements State {

    private static final String GUTTER_SYMBOL = "-";
    private final Pins firstPins;

    public FirstBowl(Pins firstPins) {
        this.firstPins = firstPins;
    }

    public static State create(Pins firstPins) {
        return new FirstBowl(firstPins);
    }

    @Override
    public State pitch(Pins secondPins) {
        if (firstPins.isSpare(secondPins)) {
            return Spare.create(firstPins);
        }

        return Miss.of(firstPins, secondPins);
    }

    @Override
    public boolean isFrameEnd() {
        return false;
    }

    @Override
    public String getSymbol() {
        return firstPins.isGutter() ? GUTTER_SYMBOL : String.valueOf(firstPins);
    }

}
