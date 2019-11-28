package bowling.domain.state;

import bowling.domain.Pin;

import java.util.Arrays;
import java.util.List;

public class SecondBowl extends BowlAbleState {
    public SecondBowl(int first, int second) {
        this(Arrays.asList(Pin.of(first), Pin.of(second)));
    }

    public SecondBowl(List<Pin> pins) {
        this.pins = pins;
    }

    @Override
    public State bowl(int countOfPin, boolean isLastFrame) {
        return new Last(nextPins(countOfPin));
    }
}
