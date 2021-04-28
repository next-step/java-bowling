package bowlingRefactor.domain.state.middelState;

import bowlingRefactor.domain.Pin;
import bowlingRefactor.domain.state.State;
import bowlingRefactor.domain.state.completeState.Last;

import java.util.Arrays;
import java.util.List;

public class SecondBowl extends Progress {

    public SecondBowl(int first, int second) {
        this.pins = Arrays.asList(Pin.of(first), Pin.of(second));
    }

    public SecondBowl(List<Pin> pins) {
        this.pins = pins;
    }

    @Override
    public State bowl(int countOfPin, boolean isLastFrame) {
        return new Last(nextPins(countOfPin));
    }

    @Override
    public boolean isSpare() {
        return false;
    }
}
