package domain.state.closed;

public class Strike extends Closed {
    private static final String STRIKE_SYMBOL = "X";

    @Override
    public String printState() {
        return STRIKE_SYMBOL;
    }
}
