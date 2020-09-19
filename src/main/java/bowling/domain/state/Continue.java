package bowling.domain.state;

import bowling.domain.pin.Pin;

public class Continue implements State {

    private final Pin first;

    private Continue(Pin first) {
        this.first = first;
    }

    public static State of(Pin felledPin) {
        return new Continue(felledPin);
    }

    @Override
    public State bowl(Pin felledPin) {
        Pin sum = first.add(felledPin);
        if (sum.isAllFelled()) {
            return Spare.of(first);
        }
        if (sum.isNotFelled()) {
            return Gutter.of();
        }
        return Miss.of(first, felledPin);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    public String getFirstPin() {
        return this.first.toString();
    }
}
