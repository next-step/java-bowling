package bowling.state.finished;

import bowling.state.State;

public abstract class Finished implements State {
    public static final String DELIMITER = "|";
    public static final int FELLED_ZERO_PINS = 0;

    @Override
    public State bowl(int felledPin) {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
