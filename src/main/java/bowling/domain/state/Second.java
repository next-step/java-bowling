package bowling.domain.state;

import bowling.domain.score.Score;

import static bowling.view.OutputView.COLUMN;

public class Second extends EndedState {
    private final State previous;

    public Second(Pin pin, State previous) {
        super(pin);
        this.previous = previous;
    }

    @Override
    public State bowl(Pin pin) {
        Pin addedPin = this.pin.add(pin);

        if (pin.isZero()) {
            return new Miss(this);
        }

        if (addedPin.isTen()) {
            return new Spare(addedPin, this);
        }

        return new Second(addedPin, this);
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

    public boolean isSecond() {
        return true;
    }

    @Override
    public String toString() {
        return previous + COLUMN + pin;
    }

    @Override
    public String toSimpleString() {
        return pin.toString();
    }
}
