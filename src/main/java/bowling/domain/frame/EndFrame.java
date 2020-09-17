package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.state.EndFrameStates;

public class EndFrame implements Frame {

    private final EndFrameStates states = new EndFrameStates();

    public static Frame of() {
        return new EndFrame();
    }

    @Override
    public void bowl(Pin felledPin) {
        states.bowl(felledPin);
    }

    @Override
    public boolean isEnd() {
        return states.isEnd();
    }

    public EndFrameStates getStates() {
        return states;
    }

}
