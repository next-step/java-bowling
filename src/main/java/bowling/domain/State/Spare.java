package bowling.domain.State;

import bowling.domain.frame.PinCount;

public class Spare implements State {

    public final static String SYMBOL = "|/";

    private final PinCount firstPinCount;

    private final PinCount secondPinCount;

    public Spare(PinCount firstPinCount, PinCount secondPinCount) {
        if (firstPinCount.isValid(secondPinCount)) {
            throw new IllegalArgumentException("투구 핀수가 너무 많습니다.");
        }
        this.firstPinCount = firstPinCount;
        this.secondPinCount = secondPinCount;
    }

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
        return firstPinCount.countInString() + SYMBOL;
    }
}
