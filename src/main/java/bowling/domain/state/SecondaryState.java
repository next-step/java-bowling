package bowling.domain.state;

import bowling.domain.score.Score;

public abstract class SecondaryState extends EndedState {
    protected final State previous;

    protected SecondaryState(Pin pin, State previous) {
        super(pin);
        this.previous = previous;
    }

    @Override
    public Score score(Score score) {
        Score nextScore = score.bowl(previous.pin);

        if (nextScore.canScore()) {
            return nextScore;
        }

        return nextScore.bowl(pin);
    }
}
