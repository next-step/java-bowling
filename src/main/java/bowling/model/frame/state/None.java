package bowling.model.frame.state;

import bowling.model.Pin;
import bowling.model.frame.State;

import static bowling.model.frame.state.Score.DEFAULT;
import static java.lang.Boolean.FALSE;

public class None implements State {

    private static final None SELF = new None();

    public static final String SYMBOL_OF_NONE = " ";

    private None() {
    }

    public static State getInstance() {
        return SELF;
    }

    @Override
    public State bowl(Pin pin) {
        return FirstState.of(pin);
    }

    @Override
    public Score getScore() {
        return DEFAULT;
    }

    @Override
    public Score calculate(Score score) {
        return score;
    }

    @Override
    public boolean isFinished() {
        return FALSE;
    }

    @Override
    public String printResult() {
        return SYMBOL_OF_NONE;
    }
}