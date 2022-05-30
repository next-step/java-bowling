package bowling.domain.state;

import bowling.domain.score.Score;

import static bowling.view.OutputView.COLUMN;

public class Spare extends EndedState {
    private static final String SYMBOL = "/";

    private final State previous;

    public Spare(Pin pin, State previous) {
        super(pin);
        this.previous = previous;
    }

    @Override
    public Score score() {
        return Score.spare(pin);
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
    public String toString() {
        return previous + COLUMN + SYMBOL;
    }

    public String toSimpleString() {
        return SYMBOL;
    }
}
