package bowling.domain.state;

import bowling.exception.ImpossiblePitchException;
import bowling.domain.Pins;

public class Spare implements State {

    private static final String CROSS_BAR = "|";

    private static final String GUTTER_SYMBOL = "-";

    private static final String SPARE_SYMBOL = "/";

    private final Pins firstPins;


    private Spare(Pins firstPins) {
        this.firstPins = firstPins;
    }

    public static State create(Pins firstPins) {
        return new Spare(firstPins);
    }

    @Override
    public State pitch(Pins pins) {
        throw new ImpossiblePitchException(Spare.class.getName());
    }

    @Override
    public boolean isFrameEnd() {
        return true;
    }

    @Override
    public String getSymbol() {
        return getFirstSymbol() + CROSS_BAR + SPARE_SYMBOL;
    }

    private String getFirstSymbol() {
        return firstPins.isGutter() ? GUTTER_SYMBOL : String.valueOf(firstPins);
    }

}
