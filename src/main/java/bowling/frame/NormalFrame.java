package bowling.frame;

import bowling.frame.state.State;
import bowling.score.Pin;

public class NormalFrame extends Frame {

    private NormalFrame(int frameNumber) {
        super(frameNumber);
    }

    public static Frame first() {
        return new NormalFrame(FIRST_FRAME_NUMBER);
    }

    public static Frame create(int frameNumber) {
        return new NormalFrame(frameNumber);
    }

    @Override
    protected Frame bowl(String fellPins) {
        Pin pin = Pin.bowl(fellPins);
        state = state.bowl(pin);
        if (state.isFinish()) {
            return next();
        }
        return this;
    }

    @Override
    protected Frame next() {
        int nextFrameNumber = this.frameNumber + INCREASE_FRAME_NUMBER;
        if (nextFrameNumber < FINAL_FRAME_NUMBER) {
            return NormalFrame.create(nextFrameNumber);
        }
        return FinalFrame.create(FINAL_FRAME_NUMBER);
    }

    @Override
    public boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public State getState() {
        return this.state;
    }
}
