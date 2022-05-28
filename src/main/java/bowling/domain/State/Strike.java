package bowling.domain.State;

public class Strike extends State {
    private static final String SYMBOL = "X";

    private final Pin pin;

    public Strike(Pin pin) {
        this.pin = pin;
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
        return SYMBOL;
    }
}
