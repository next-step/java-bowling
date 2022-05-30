package bowling.domain.state;

import bowling.domain.score.Score;

import static bowling.view.OutputView.COLUMN;

public class Miss extends EndedState {
    private static final String SYMBOL = "-";

    private final State previous;

    public Miss(State previous) {
        this(Pin.zero(), previous);
    }

    public Miss(Pin pin, State previous) {
        super(pin);
        this.previous = previous;
    }

    @Override
    public State bowl(Pin pin) {
        return null;
    }

    @Override
    public Score score() {
        return previous.score().bowl(pin);
    }

    @Override
    public Score score(Score score) {
        Score nextScore = score.bowl(previous.pin);

        if (nextScore.canScore()) {
            return nextScore;
        }

        return nextScore.bowl(pin);
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public boolean isMiss() {
        return true;
    }

    @Override
    public String toString() {
        return previous + COLUMN + SYMBOL;
    }

    @Override
    public String toSimpleString() {
        return SYMBOL;
    }
}
