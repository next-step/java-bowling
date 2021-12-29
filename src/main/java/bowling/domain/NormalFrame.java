package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class NormalFrame implements Frame {

    private final State state;

    public NormalFrame() {
        this(new Ready());
    }

    private NormalFrame(State state) {
        this.state = state;
    }

    @Override
    public NormalFrame bowl(Pins pins) {
        validFinished();
        return new NormalFrame(state.bowl(pins));
    }

    @Override
    public String mark() {
        return state.mark();
    }

    @Override
    public boolean isFinished() {
        return state.isFinished();
    }

    private void validFinished() {
        if (isFinished()) {
            throw new RuntimeException("진행할 수 없는 프레임 입니다.");
        }
    }

}
