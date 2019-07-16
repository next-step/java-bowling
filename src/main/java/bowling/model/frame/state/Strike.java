package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;

import static bowling.model.Pins.DOWN_ALL;
import static bowling.model.frame.state.Score.STRIKE;
import static java.lang.Boolean.TRUE;

public class Strike extends FirstState {

    private static final String SYMBOL_OF_STRIKE = "X";
    private static final Strike SELF = new Strike();
    private static final Pins firstBowl = DOWN_ALL;

    private Strike() {
        super(firstBowl);
    }

    static State getInstance() {
        return SELF;
    }

    static boolean isMatch(Pins pins) {
        return firstBowl.equals(pins);
    }

    @Override
    public State bowl(Pins pins) {
        throw new CanNotBowlException();
    }

    @Override
    public Score getScore() {
        return STRIKE;
    }

    @Override
    public String printResult() {
        return SYMBOL_OF_STRIKE;
    }

    @Override
    public boolean isFinished() {
        return TRUE;
    }
}