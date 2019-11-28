package bowling.domain.state;

import bowling.domain.Pin;

public class Ready extends BowlAbleState {
    @Override
    public State bowl(int countOfPin, boolean isLastFrame) {
        if (!isLastFrame && countOfPin == Pin.ALL_PIN_COUNT) {
            return new Strike();
        }

        return new FirstBowl(nextPins(countOfPin));
    }
}
