package bowling.domain.state;

import bowling.domain.pin.Pin;

public class Miss implements State {

    private final Pin first;
    private final Pin second;

    private Miss(Pin first, Pin second) {
        this.first = first;
        this.second = second;
    }

    public static State of(Pin firstPin, Pin felledPin) {
        return new Miss(firstPin, felledPin);
    }

    @Override
    public State bowl(Pin felled) {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public String makeSymbol() {
        return first + Symbol.MISS.toString() + second;
    }
}
