package domain.frame;

import domain.Pins;
import domain.state.Bonus;
import domain.state.Ready;
import domain.state.State;

public class LastFrame implements Frame {

    private State state;

    public LastFrame() {
        this.state = new Bonus();
    }

    @Override
    public State setKnockedDownPins(Pins knockedDown) {
        return state.bowl(knockedDown);
    }
}
