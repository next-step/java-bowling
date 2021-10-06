package step4.domain;

import step4.domain.state.Ready;
import step4.domain.state.State;

public class NormalFrame {
    private Score score;
    private State state;

    public NormalFrame() {
        this.state = new Ready();
    }

    public void throwBowl(int falledPins) {
        state = state.throwBowl(falledPins);
    }

    public State state() {
        return state;
    }
}
