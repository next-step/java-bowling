package bowling.domain.state;

import bowling.domain.Frame;

public class Gutter implements ThrowingState {
    private Frame frame;

    public Gutter(Frame frame) {
        this.frame = frame;
    }

    @Override
    public ThrowingState bowl(int pins) {
        if (pins == 10) {
            return new Spare(frame);
        }
        if (pins == 0) {
            return new Miss(frame);
        }
        return new SecondBowl(frame);
    }

    @Override
    public String symbol() {
        return "-";
    }
}
