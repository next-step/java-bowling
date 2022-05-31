package bowling.domain.state.last;

import bowling.domain.Frame;
import bowling.domain.state.ThrowingState;
import bowling.domain.state.normal.Gutter;
import bowling.domain.state.normal.Strike;

public class SecondReady implements ThrowingState {
    private Frame frame;

    public SecondReady(Frame frame) {
        this.frame = frame;
    }

    @Override
    public ThrowingState bowl(int pins) {
        if (pins == 10) {
            return new Strike(frame);
        }
        if (pins == 0) {
            return new Gutter(frame);
        }
        return new SecondBowl(frame);
    }

    @Override
    public String symbol() {
        return "";
    }
}
