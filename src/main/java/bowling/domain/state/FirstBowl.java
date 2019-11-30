package bowling.domain.state;

import bowling.domain.Pin;

import java.util.Collections;
import java.util.List;

public class FirstBowl extends BowlAbleState {
    public FirstBowl(int first) {
        this(Collections.singletonList(Pin.of(first)));
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
        if (sum == Pin.ALL_PIN_COUNT) {
            return new Spare(nextPins);
        }

        return new Miss(nextPins);
    }
}
