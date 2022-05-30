package bowling.domain.State;

import bowling.domain.score.Score;

public class Ready extends RunningState {
    public Ready() {
        this(Pin.zero());
    }

    public Ready(Pin pin) {
        super(pin);
    }

    @Override
    public State bowl(Pin pin) {
        if (pin.isTen()) {
            return new Strike(pin);
        }

        if (pin.isZero()) {
            return new Gutter();
        }

        return new First(pin);
    }

    @Override
    public Score score() {
        return Score.unScorable();
    }

    @Override
    public Score score(Score score) {
        return score;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public String toSimpleString() {
        return toString();
    }
}
