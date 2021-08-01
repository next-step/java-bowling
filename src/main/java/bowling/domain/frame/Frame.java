package bowling.domain.frame;

import bowling.domain.pin.DownedPins;
import bowling.domain.state.State;
import bowling.dto.StateDtos;

import java.util.List;

public abstract class Frame {
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

    protected void appendFrame(List<Frame> frames) {}

    public StateDtos getFrameStates() {
        return StateDtos.from(state.getState());
    }
}
