package bowling.domian.frame;

import bowling.domian.state.Ready;
import bowling.domian.state.State;

public class NormalFrame implements Frame {
    private final int frameNumber;
    private State state;

    private NormalFrame(int frameNumber) {
        this.frameNumber = frameNumber;
        this.state = new Ready();
    }

    public static NormalFrame firstFrame() {
        return new NormalFrame(1);
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public Frame bowl(int falledPinsCount) {
        State nextState = state.bowl(falledPinsCount);

        if (nextState.isFinished()) {
            // TODO: 2020/08/15 리팩토링
            if (frameNumber < 9) {
                return new NormalFrame(frameNumber + 1);
            }

            return new FinalFrame();
        }

        this.state = nextState;
        return this;
    }
}
