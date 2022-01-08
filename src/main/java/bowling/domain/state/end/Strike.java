package bowling.domain.state.end;

public class Strike extends EndedState {

    private static final String STRIKE_SYMBOL = "X";

    @Override
    public String symbol() {
        return STRIKE_SYMBOL;
    }

    @Override
    public boolean isMiss() {
        return false;
    }
}
