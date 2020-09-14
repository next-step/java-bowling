package bowling.domain;

import bowling.domain.state.Last;

public class LastFrame extends Frame {

    protected LastFrame(int number) {
        super(number);
        state = new Last();
    }

    public static Frame from() {
        return new LastFrame(LAST_FRAME_NUMBER);
    }

    @Override
    public Frame hit(int count) {
        state.roll(count);
        return this;
    }
}
