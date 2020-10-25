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
        return this;
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
