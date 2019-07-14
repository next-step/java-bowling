package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;

import static bowling.model.Pins.MAX;

public class Strike extends FirstState {

    static final int STRIKE_BONUS_COUNT = 2;
    private static final String SYMBOL_OF_STRIKE = "X";
    private static final Strike SELF = new Strike();
    private static final Pins firstBowl = Pins.DOWN_ALL;

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
    public boolean isFinished() {
        return true;
    }

    @Override
    public String printResult() {
        return SYMBOL_OF_STRIKE;
    }

    @Override
    public Score getScore() {
        return Score.of(STRIKE_BONUS_COUNT, MAX);
    }
}
