package bowling.domain.state;

import bowling.domain.Frame;

public class Ready implements RunningState {
    private Frame frame;

    public Ready(Frame frame) {
        this.frame = frame;
    }

    @Override
    public ThrowingState bowl() {
        int first = frame.first();
        if (first == 10) {
            return new Strike();
        }

        if (first == 0) {
            return new Gutter(frame);
        }

        return new FirstBowl(frame);
    }

    @Override
    public String symbol() {
        return "";
    }

    @Override
    public Frame frame() {
        return this.frame;
    }
}
