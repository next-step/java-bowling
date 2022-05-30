package bowling.domain.state;

import bowling.domain.Frame;

public class FirstBowl implements ThrowingState {
    private Frame frame;

    public FirstBowl(Frame frame) {
        this.frame = frame;
    }

    @Override
    public ThrowingState bowl(int pins) {
        int previous = frame.first();
        if (previous + pins == 10) {
            return new Spare(frame);
        }
        if (previous + pins == 0) {
            return new Miss(frame);
        }
        return new SecondBowl(frame);
    }

    @Override
    public String symbol() {
        return String.valueOf(frame.first());
    }
}
