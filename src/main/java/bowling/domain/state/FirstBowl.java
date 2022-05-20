package bowling.domain.state;

import bowling.domain.Frame;

public class FirstBowl implements RunningState {
    private Frame frame;

    public FirstBowl(Frame frame) {
        this.frame = frame;
    }

    @Override
    public ThrowingState bowl() {
        int first = frame.first();
        int second = frame.second();
        if (first + second == 10) {
            return new Spare(this);
        }

        if (first + second == 0) {
            return new Miss(this);
        }

        return new SecondBowl(this);
    }

    @Override
    public String symbol() {
        return String.valueOf(frame.first());
    }

    @Override
    public Frame frame() {
        return this.frame;
    }
}
