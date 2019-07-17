package bowling.model.frame.state;

import bowling.model.DownPin;
import bowling.model.frame.State;

import static bowling.model.frame.state.Score.*;
import static java.lang.Boolean.FALSE;

public class Ready implements State {

    private static final Ready SELF = new Ready();

    private static final String SYMBOL = " ";

    private Ready() {
    }

    public static State getInstance() {
        return SELF;
    }

    @Override
    public State bowl(DownPin first) {
        if (Gutter.isMatch(first)) {
            return Gutter.getInstance();
        }
        if (Strike.isMatch(first)) {
            return Strike.getInstance();
        }
        return Hit.valueOf(first);
    }

    @Override
    public Score getScore() {
        return DEFAULT;
    }

    @Override
    public Score calculate(Score score) {
        return Score.of(ZERO_OF_COUNT, DEFAULT_SCORE);
    }

    @Override
    public boolean isFinished() {
        return FALSE;
    }

    @Override
    public String printResult() {
        return SYMBOL;
    }
}