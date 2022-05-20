package bowling.domain.state;

import bowling.domain.Frame;

public class Gutter implements RunningState {
    private Frame frame;

    public Gutter(Frame frame) {
        this.frame = frame;
    }

    @Override
    public ThrowingState bowl() {
        int second = frame.second();
        if (second == 10) {
            return new Spare(this);
        }

        if (second == 0) {
            return new Miss(this);
        }

        return new SecondBowl(this);
    }

    @Override
    public String symbol() {
        return "-";
    }

    @Override
    public Frame frame() {
        return this.frame;
    }
}
