package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;

public class Strike extends FirstState {

    private static final String SYMBOL_OF_STRIKE = "X";
    private static final Strike SELF = new Strike();
    private static final Pins firstBowl = Pins.DOWN_ALL;

    private Strike() {
    }

    static State getInstance() {
        return SELF;
    }

    static boolean isMatch(Pins pins) {
        return firstBowl.equals(pins);
    }

    @Override
    public State bowl(Pins pins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public String printResult() {
        return SYMBOL_OF_STRIKE;
    }
}
