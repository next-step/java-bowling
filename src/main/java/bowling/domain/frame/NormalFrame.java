package bowling.domain.frame;

import bowling.domain.pins.Pins;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class NormalFrame implements Frame {

    private final int frameNumber;
    private State state;

    private NormalFrame(int frameNumber) {
        this.frameNumber = frameNumber;
        state = Ready.of();
    }

    public static NormalFrame of(int frameNumber) {
        return new NormalFrame(frameNumber);
    }

    @Override
    public Frame bowl(Pins pins) {
        state = state.bowl(pins);
        if (state.isFinish()) {
            return createFrame();
        }
        return this;
    }

    @Override
    public boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public State getState() {
        return state;
    }

    private Frame createFrame() {
        return new NormalFrame(frameNumber + 1);
    }
}
