package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;

public class Miss implements State {

    private final Pins first;
    private final Pins second;

    private Miss(Pins first, Pins second) {
        this.first = first;
        this.second = second;
    }

    static State valueOf(Pins first, Pins second) {
        if (first.equals(Pins.DOWN_ALL) || first.sum(second).equals(Pins.DOWN_ALL)) {
            throw new IllegalArgumentException();
        }
        return new Miss(first, second);
    }

    @Override
    public State bowl(Pins pins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public String printResult() {
        return String.format("   %s|%s   ", first, second);
    }
}
