package bowling.domain.state;

import bowling.domain.score.Score;

public class Gutter extends RunningState {
    private static final String SYMBOL = "-";

    public Gutter() {
        this(Pin.zero());
    }

    public Gutter(Pin pin) {
        super(pin);
    }

    @Override
    public State bowl(Pin pin) {
        if (pin.isTen()) {
            return new Spare(pin, this);
        }

        if (pin.isZero()) {
            return new Miss(this);
        }

        return new Second(pin, this);
    }

    @Override
    public Score score() {
        return Score.unScorable(pin);
    }

    @Override
    public String toString() {
        return SYMBOL;
    }

    @Override
    public String toSimpleString() {
        return toString();
    }
}
