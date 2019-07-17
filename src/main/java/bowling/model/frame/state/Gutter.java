package bowling.model.frame.state;

import bowling.model.Pin;
import bowling.model.frame.State;

import static bowling.model.Pin.DOWN_ZERO;

public class Gutter extends FirstState {

    static final String PRINT_SYMBOL_OF_GUTTER = "-";
    private static final Pin firstBowl = DOWN_ZERO;
    private static final Gutter SELF = new Gutter();

    private Gutter() {
        super(firstBowl);
    }

    static State getInstance() {
        return SELF;
    }

    static boolean isMatch(Pin downPin) {
        return firstBowl.equals(downPin);
    }

    @Override
    public String printResult() {
        return PRINT_SYMBOL_OF_GUTTER;
    }
}
