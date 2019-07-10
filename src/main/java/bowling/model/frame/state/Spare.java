package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;

public class Spare implements State {

    private Pins first;

    private Spare(Pins first) {
        this.first = first;
    }

    public static State valueOf(Pins first) {
        return new Spare(first);
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
        return String.format("   %s|/   ", first);
    }
}
