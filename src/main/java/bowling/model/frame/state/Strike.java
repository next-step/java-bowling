package bowling.model.frame.state;

import bowling.model.Pin;
import bowling.model.frame.State;

import static bowling.model.Pin.DOWN_ALL;
import static bowling.model.frame.state.Score.STRIKE;
import static java.lang.Boolean.TRUE;

public class Strike extends FirstState {

    private static final String SYMBOL_OF_STRIKE = "X";
    private static final Strike SELF = new Strike();
    private static final Pin firstBowl = DOWN_ALL;

    private Strike() {
        super(firstBowl);
    }

    static State getInstance() {
        return SELF;
    }

    static boolean isMatch(Pin pin) {
        return firstBowl.equals(pin);
    }

    @Override
    public State bowl(Pin pin) {
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