package step3;

import step3.state.Ready;
import step3.state.State;

public class Frame {
    private State state;

    public Frame() {
        this.state = new Ready();
    }

    public void bowl(int fallenPins) {
        state = state.bowl(fallenPins);
    }

    public State getState() {
        return state;
    }


}
