package bowling.domain.state;

public class Strike extends EndedState {
    private static final String STRIKE_SYMBOL = "X";

    public static ThrowingState create() {
        return new Strike();
    }

    @Override
    public String symbol() {
        return STRIKE_SYMBOL;
    }
}
