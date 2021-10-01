package bowling.domain.state.running;

import bowling.domain.score.Pin;
import bowling.domain.state.State;
import bowling.domain.state.finish.Strike;

public class Ready extends Running {

    public Ready() {
    }

    @Override
    public State bowl(Pin pin) {
        if (pin.isStrike()) {
            return new Strike();
        }
        return new FirstBowl(pin);
    }

}
