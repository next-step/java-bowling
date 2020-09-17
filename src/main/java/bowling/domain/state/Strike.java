package bowling.domain.state;

import bowling.domain.pin.Pin;

public class Strike implements State {

    public static final String STRIKE_MARK = "X";

    private Strike() {
    }

    public static State of() {
        return new Strike();
    }

    @Override
    public State bowl(Pin pin) {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public String view() {
        return STRIKE_MARK;
    }
}
