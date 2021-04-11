package bowling.domain.State;

import bowling.domain.frame.PinCount;

public class Strike implements State{

    private final static String SYMBOL = "X";

    @Override
    public State newState(PinCount pinCount) {
        throw new IllegalStateException("이미 종료된 상태입니다.");
    }

    @Override
    public boolean isClosed() {
        return true;
    }

    @Override
    public String stateInString() {
        return SYMBOL;
    }

}
