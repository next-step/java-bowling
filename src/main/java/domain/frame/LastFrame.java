package domain.frame;

import domain.Pins;
import domain.bowling.LastFrameSet;
import domain.state.State;

public class LastFrame implements Frame {

    private LastFrameSet bowling;

    public LastFrame() {
        this.bowling = new LastFrameSet();
    }

    @Override
    public State setKnockedDownPins(Pins knockedDown) {

        return null;
    }

    @Override
    public State getState() {
        return null;
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public FrameIndex getFrameNumber() {
        return null;
    }
}
