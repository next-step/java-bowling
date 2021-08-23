package bowling.domain.frame;

import bowling.domain.pins.Pins;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.Collections;
import java.util.List;

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
    public int getFrameNumber() {
        return frameNumber;
    }

    @Override
    public void bowl(Pins pins) {
        state = state.bowl(pins);
    }

    @Override
    public boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public List<State> getState() {
        return Collections.singletonList(state);
    }
}
