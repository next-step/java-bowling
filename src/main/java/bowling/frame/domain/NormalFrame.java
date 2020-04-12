package bowling.domain.frame.domain;

import bowling.domain.state.State;
import bowling.domain.state.ready.First;

public class NormalFrame implements Frame {

    private State state = First.of();
    private final Remains remains;

    private NormalFrame() {
        this.remains = Remains.two();
    }

    static Frame of() {
        return new NormalFrame();
    }

    @Override
    public void bowl(int felledPin) {
        state = state.bowl(felledPin);
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
