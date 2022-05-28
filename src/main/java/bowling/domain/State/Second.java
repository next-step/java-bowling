package bowling.domain.State;

import static bowling.view.OutputView.COLUMN;

public class Second extends State {
    private final Pin pin;
    private final State previous;

    public Second(Pin pin, State previous) {
        this.pin = pin;
        this.previous = previous;
    }

    @Override
    public State bowl(Pin pin) {
        return null;
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public String toString() {
        return previous + COLUMN + pin;
    }
}
