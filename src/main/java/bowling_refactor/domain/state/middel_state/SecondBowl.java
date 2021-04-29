package bowling_refactor.domain.state.middel_state;

import bowling_refactor.domain.Pin;
import bowling_refactor.domain.state.State;
import bowling_refactor.domain.state.complete_state.Last;

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
