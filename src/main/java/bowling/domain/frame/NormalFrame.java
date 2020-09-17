package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class NormalFrame implements Frame {

    private State state = Ready.of();

    private NormalFrame() {
    }

    public static Frame of() {
        return new NormalFrame();
    }

    @Override
    public void bowl(Pin felledPin) {
        this.state = state.bowl(felledPin);
    }

    @Override
    public boolean isEnd() {
        return state.isEnd();
    }

    public State getState() {
        return state;
    }
}
