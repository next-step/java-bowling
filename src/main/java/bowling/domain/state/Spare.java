package bowling.domain.state;

import bowling.domain.score.Score;

import static bowling.view.OutputView.COLUMN;

public class Spare extends SecondaryState {
    private static final String SYMBOL = "/";

    public Spare(Pin pin, State previous) {
        super(pin, previous);
    }

    @Override
    public Score score() {
        return Score.spare(pin);
    }

    @Override
    public String toString() {
        return previous + COLUMN + SYMBOL;
    }

    public String toSimpleString() {
        return SYMBOL;
    }
}
