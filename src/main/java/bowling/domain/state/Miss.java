package bowling.domain.state;

import bowling.domain.Frame;
import bowling.exception.EndedFrameException;

public class Miss implements ThrowingState {
    private Frame frame;

    public Miss(Frame frame) {
        this.frame = frame;
    }

    @Override
    public ThrowingState bowl(int pins) {
        throw new EndedFrameException();
    }

    @Override
    public String symbol() {
        return "│-";
    }
}
