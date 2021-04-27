package bowlingRefactor.domain.state.middelState;

import bowlingRefactor.domain.Pin;
import bowlingRefactor.domain.state.State;
import bowlingRefactor.domain.state.completeState.Strike;

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
