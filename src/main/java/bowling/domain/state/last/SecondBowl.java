package bowling.domain.state.last;

import bowling.domain.Frame;
import bowling.domain.state.ThrowingState;
import bowling.domain.state.normal.Miss;
import bowling.domain.state.normal.Spare;
import bowling.exception.EndedFrameException;

public class SecondBowl implements ThrowingState {
    private Frame frame;

    public SecondBowl(Frame frame) {
        this.frame = frame;
    }

    @Override
    public ThrowingState bowl(int pins) {
        return new ThirdBowl(frame);
    }

    @Override
    public String symbol() {
        if (frame.second() == 0) {
            return "│-";
        }
        return "│" + frame.second();
    }
}
