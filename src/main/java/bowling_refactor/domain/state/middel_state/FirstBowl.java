package bowling_refactor.domain.state.middel_state;

import bowling_refactor.domain.Pin;
import bowling_refactor.domain.state.State;
import bowling_refactor.domain.state.complete_state.Miss;
import bowling_refactor.domain.state.complete_state.Spare;

import java.util.Arrays;
import java.util.List;

public class FirstBowl extends Progress {

    public FirstBowl(int first) {
        this(Arrays.asList(Pin.of(first)));
    }

    public FirstBowl(List<Pin> pins) {
        this.pins = pins;
    }

    @Override
    public State bowl(int countOfPin, boolean isLastFrame) {
        List<Pin> nextPins = nextPins(countOfPin);
        if (isLastFrame) {
            return new SecondBowl(nextPins);
        }

        int sum = sum() + countOfPin;
        if (sum == Pin.MAX_PIN) {
            return new Spare(nextPins);
        }

        return new Miss(nextPins);
    }

    @Override
    public boolean isSpare() {
        return false;
    }
}
