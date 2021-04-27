package bowlingRefactor.domain.state.middelState;

import bowlingRefactor.domain.Pin;
import bowlingRefactor.domain.state.State;
import bowlingRefactor.domain.state.completeState.Miss;
import bowlingRefactor.domain.state.completeState.Spare;

import java.util.ArrayList;
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
