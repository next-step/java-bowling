package bowling_refactor.domain.state.middel_state;

import bowling_refactor.domain.Pin;
import bowling_refactor.domain.state.State;
import bowling_refactor.domain.state.complete_state.Strike;

public class Ready extends Progress {

    @Override
    public State bowl(int countOfPin, boolean isLastFrame) {
        if (!isLastFrame && countOfPin == Pin.MAX_PIN) {
            return new Strike();
        }

        return new FirstBowl(nextPins(countOfPin));
    }

    @Override
    public boolean isSpare() {
        return false;
    }

}
