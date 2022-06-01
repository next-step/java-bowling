package bowling.domain.state;

import bowling.domain.score.Score;

public abstract class State {
    protected final Pin pin;

    protected State(Pin pin) {
        this.pin = pin;
    }

    public abstract State bowl(Pin pin);


    public abstract boolean isDone();

    public boolean isNotTen() {
        return !pin.isTen();
    }

    public abstract Score score();

    public Score score(Score score) {
        return score.bowl(pin);
    }

    public Score simpleScore() {
        return new Score(pin, 0);
    }

    public static Ready ready() {
        return new Ready();
    }

    public abstract String toString();

    public abstract String toSimpleString();
}
