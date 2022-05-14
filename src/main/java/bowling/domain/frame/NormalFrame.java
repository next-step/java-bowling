package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.state.State;
import bowling.domain.state.Ready;

public class NormalFrame implements Frame {

    private State state;

    private NormalFrame() {
        this.state = Ready.create();
    }

    public static Frame create() {
        return new NormalFrame();
    }

    @Override
    public boolean isFrameEnd() {
        return state.isFrameEnd();
    }

    @Override
    public void pitch(Pins pins) {
        this.state = state.pitch(pins);
    }

    @Override
    public String getSymbol() {
        return state.getSymbol();
    }

}
