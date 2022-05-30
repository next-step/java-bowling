package bowling.domain.state;

import bowling.domain.score.Score;

public abstract class State {
    protected final Pin pin;

    protected State(Pin pin) {
        this.pin = pin;
    }

    public abstract State bowl(Pin pin);

    public static Ready ready() {
        return new Ready();
    }

    public abstract boolean isDone();

    public abstract String toString();

    public abstract String toSimpleString();

    public boolean isMiss() {
        return false;
    }

    public boolean isSecond() {
        return false;
    }

    public abstract Score score();

    public abstract Score score(Score score);

    public Score simpleScore() {
        return new Score(pin, 0);
    }
}
