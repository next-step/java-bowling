package bowling.domain.state;

import bowling.domain.pin.Pin;

public class Gutter implements State {

    private Gutter() {
    }

    public static State of() {
        return new Gutter();
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
        return Symbol.GUTTER.toString();
    }
}
