package bowling.frame.domain;

import bowling.state.State;
import bowling.state.finish.LastFrameState;

public class LastFrame implements Frame {

    private State state = LastFrameState.init();

    static Frame of() {
        return new LastFrame();
    }

    @Override
    public void bowl(int felledPins) {
        state = state.bowl(felledPins);
    }

    @Override
    public boolean isFinished() {
        return state.isFinished();
    }

    @Override
    public String view() {
        return state.view();
    }

}
