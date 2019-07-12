package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;

import static bowling.model.Pins.DOWN_ZERO;

public class Gutter extends FirstState {

    static final String PRINT_SYMBOL_OF_GUTTER = "-";
    private static final Gutter SELF = new Gutter();
    private static final Pins firstBowl = DOWN_ZERO;

    private Gutter() {
        super(firstBowl);
    }

    static State getInstance() {
        return SELF;
    }

    static boolean isMatch(Pins downPins) {
        return firstBowl.equals(downPins);
    }

    @Override
    public String printResult() {
        return PRINT_SYMBOL_OF_GUTTER;
    }
}
