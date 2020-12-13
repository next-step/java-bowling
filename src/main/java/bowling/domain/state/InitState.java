package bowling.domain.state;

import static bowling.domain.FallingPinCount.ALL_PIN_DOWN;

import bowling.domain.FallingPinCount;

public class InitState implements State {

    public static final String EMPTY = "";
    private static final State INIT = new InitState();

    protected InitState() {
    }

    public static State getInstance() {
        return INIT;
    }

    @Override
    public State roll(FallingPinCount fallingPinCount) {
        if (ALL_PIN_DOWN.equals(fallingPinCount)) {
            return new Strike(fallingPinCount);
        }
        return new Playing(fallingPinCount);
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public String reportState() {
        return EMPTY;
    }
}
