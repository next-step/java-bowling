package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;

public class None implements State {

    private static final None SELF = new None();

    private static final String SYMBOL_OF_NONE = " ";

    private None() {
    }

    public static State getInstance() {
        return SELF;
    }

    @Override
    public State bowl(Pins pins) {
        return FirstState.of(pins);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public String printResult() {
        return SYMBOL_OF_NONE;
    }
}