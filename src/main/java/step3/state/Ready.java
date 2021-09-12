package step3.state;

import step3.FirstBowl;
import step3.state.State;
import step3.state.Strike;

public class Ready implements State {

    public State bowl(int fallenPins) {
        if (fallenPins == 10) {
            return new Strike();
        }
        return new FirstBowl(fallenPins);
    }
}
