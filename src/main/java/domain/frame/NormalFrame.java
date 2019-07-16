package domain.frame;

import domain.Pins;
import domain.state.Ready;
import domain.state.State;

public class NormalFrame implements Frame {

    private State state;

    public NormalFrame() {
        this.state = new Ready();
    }

    @Override
    public State setKnockedDownPins(Pins knockedDown) {
        state = state.bowl(knockedDown);
        return state;
    }

    @Override
    public State getState() {
        return state;
    }
}
