package bowling.domain.state;

import bowling.exception.ImpossiblePitchException;
import bowling.domain.Pins;

public class Miss implements State {

    private static final String GUTTER_SYMBOL = "-";

    private static final String VERTICAL_BAR = "|";

    private final Pins firstPins;

    private final Pins secondPins;

    private Miss(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static State of(Pins firstPins, Pins secondPins) {
        return new Miss(firstPins, secondPins);
    }

    @Override
    public State pitch(Pins pins) {
        throw new ImpossiblePitchException(Miss.class.getName());
    }

    @Override
    public boolean isFrameEnd() {
        return true;
    }

    @Override
    public String getSymbol() {
        return convertIfGutter(firstPins) + VERTICAL_BAR + convertIfGutter(secondPins);
    }

    private String convertIfGutter(Pins pins) {
        return pins.isGutter() ? GUTTER_SYMBOL : pins.toString();
    }

}
