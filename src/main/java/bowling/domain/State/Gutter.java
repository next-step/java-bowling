package bowling.domain.State;

import bowling.domain.frame.PinCount;

public class Gutter implements State {

    public final static String SYMBOL = "-";

    @Override
    public State newState(PinCount pinCount) {
        return new Miss(new PinCount(0),pinCount);
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public String stateInString() {
        return SYMBOL;
    }
}
