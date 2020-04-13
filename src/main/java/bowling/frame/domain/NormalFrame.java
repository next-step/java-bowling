package bowling.frame.domain;

import bowling.state.State;
import bowling.state.ready.First;

public class NormalFrame implements Frame {

    private State state = First.of();

    private NormalFrame() {
    }

    static Frame of() {
        return new NormalFrame();
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
