package bowling.domain.frame;

import bowling.domain.state.FrameState;
import bowling.domain.state.Ready;

public class NormalFrame implements IFrame {

    private FrameState state;

    public NormalFrame() {
        this.state = new Ready();
    }

    @Override
    public FrameState bowl(int pins) {
        this.state = state.bowl(pins);
        return state;
    }

    public FrameState state() {
        return state;
    }
}
