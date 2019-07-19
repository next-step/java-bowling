package bowling.model.frame.state;

import bowling.model.DownPin;
import bowling.model.frame.State;

import static bowling.model.DownPin.DOWN_ALL;
import static java.lang.Boolean.TRUE;

public class Strike extends FirstState {

    private static final String SYMBOL = "X";
    private static final Strike SELF = new Strike();
    private static final DownPin STRIKE_PIN = DOWN_ALL;

    private Strike() {
        super(STRIKE_PIN);
    }

    static State getInstance() {
        return SELF;
    }

    static boolean isMatch(DownPin downPin) {
        return STRIKE_PIN.equals(downPin);
    }

    @Override
    public State bowl(DownPin second) {
        throw new CanNotBowlException();
    }

    @Override
    public Score getScore() {
        return Score.STRIKE;
    }

    @Override
    public boolean isFinished() {
        return TRUE;
    }

    @Override
    public String printResult() {
        return SYMBOL;
    }
}