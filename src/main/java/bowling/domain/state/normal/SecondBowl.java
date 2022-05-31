package bowling.domain.state.normal;

import bowling.domain.Frame;
import bowling.domain.state.ThrowingState;
import bowling.exception.EndedFrameException;

public class SecondBowl implements ThrowingState {
    private Frame frame;

    public SecondBowl(Frame frame) {
        this.frame = frame;
    }

    @Override
    public ThrowingState bowl(int pins) {
        throw new EndedFrameException();
    }

    @Override
    public String symbol() {
        if (frame.second() == 0) {
            return "│-";
        }
        return "│" + frame.second();
    }
}
