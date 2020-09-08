package camp.nextstep.edu.rebellion.bowling.domain.status;

public class Strike implements FrameStatus {
    private static final String STRIKE_SYMBOL = "X";

    @Override
    public String makeSymbol() {
        return STRIKE_SYMBOL;
    }
}
