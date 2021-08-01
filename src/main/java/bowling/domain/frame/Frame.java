package bowling.domain.frame;

import bowling.domain.pin.DownedPins;
import bowling.domain.state.State;

import java.util.List;

public abstract class Frame {
    public static final int LIMIT_OF_FRAME_SIZE = 10;

    protected State state;

    protected Frame(State state) {
        this.state = state;
    }

    public void downPins(DownedPins downedPins) {
        this.state = state.downPins(downedPins);
    }

    protected boolean isEnd() {
        return state.isEnd();
    }

    protected abstract void appendFrame(List<Frame> frames);
}
