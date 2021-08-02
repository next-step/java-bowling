package bowling.domain.frame;

import bowling.domain.pin.DownedPins;
import bowling.domain.state.State;
import bowling.dto.StateDtos;

import java.util.List;

public abstract class Frame {
    public static final int START_NUMBER_OF_FRAME = 1;
    public static final int END_NUMBER_OF_FRAME = 10;

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

    public boolean isBowlingEnd() {
        return false;
    }
}
