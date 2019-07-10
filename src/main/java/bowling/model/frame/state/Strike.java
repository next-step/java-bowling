package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;
import bowling.utils.Pretty;

public class Strike implements State {

    private static final Strike SELF = new Strike();
    private static final Pins score = Pins.DOWN_ALL;

    private Strike() {
    }

    static State getInstance() {
        return SELF;
    }

    static boolean isMatch(Pins pins) {
        return score.equals(pins);
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
        return Pretty.alignCenter("X");
    }
}
