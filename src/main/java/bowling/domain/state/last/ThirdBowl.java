package bowling.domain.state.last;

import bowling.domain.Frame;
import bowling.domain.LastFrame;
import bowling.domain.state.ThrowingState;
import bowling.exception.EndedFrameException;

public class ThirdBowl implements ThrowingState {
    private Frame frame;

    public ThirdBowl(Frame frame) {
        this.frame = frame;
    }

    @Override
    public ThrowingState bowl(int pins) {
        throw new EndedFrameException();
    }

    @Override
    public String symbol() {
        return "│" + String.valueOf(((LastFrame) frame).third());
    }
}
