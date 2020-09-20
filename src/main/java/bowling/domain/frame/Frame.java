package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.state.Gutter;
import bowling.domain.state.Miss;
import bowling.domain.state.State;

public interface Frame {

    static Frame normalFrame() {
        return NormalFrame.of();
    }

    static Frame endFrame() {
        return EndFrame.of();
    }

    void bowl(Pin felledPin);

    boolean isEnd();

    default boolean isFrameFinish(State state) {
        return state instanceof Miss || state instanceof Gutter;
    }
}
