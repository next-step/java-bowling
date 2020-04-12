package bowling.domain.frame.domain;

import bowling.domain.state.State;
import bowling.domain.state.ready.First;

public class LastFrame implements Frame {
    private static final int SCORE_ZERO = 0;

    private State state = First.of();
    private final Remains remains;

    private LastFrame() {
        this.remains = Remains.three();
    }

    static Frame of() {
        return new LastFrame();
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
