package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;
import bowling.domain.value.Pins;

public class NormalFrame extends Frame {

    private State state;

    public NormalFrame() {

        this.state = new Ready();
    }

    public static Frame create() {
        return new NormalFrame();
    }

    @Override
    public boolean isFrameOver() {
        return state.isFinished();
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public void bowl(Pins pins) {
        this.state = state.bowl(pins);
    }

    @Override
    public String getMark() {
        return state.getMark();
    }

    public State getState() {
        return state;
    }
}
