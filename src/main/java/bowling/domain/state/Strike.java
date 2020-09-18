package bowling.domain.state;

import bowling.domain.pin.Pin;

public class Strike implements State {

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
    public String makeSymbol() {
        return Symbol.STRIKE.toString();
    }
}
