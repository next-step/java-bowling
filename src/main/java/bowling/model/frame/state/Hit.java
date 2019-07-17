package bowling.model.frame.state;

import bowling.model.InvalidPinsException;
import bowling.model.Pin;
import bowling.model.frame.State;

import static bowling.model.Pin.MAX;
import static bowling.model.Pin.MIN;

public class Hit extends FirstState {

    static final int MIN_COUNT_OF_PINS_IN_HIT = MIN + 1;
    static final int MAX_COUNT_OF_PINS_IN_HIT = MAX - 1;

    private Hit(Pin firstBowl) {
        super(firstBowl);
    }

    static State valueOf(Pin firstBowl) {
        if (Gutter.isMatch(firstBowl) || Strike.isMatch(firstBowl)) {
            throw new InvalidPinsException(MIN_COUNT_OF_PINS_IN_HIT, MAX_COUNT_OF_PINS_IN_HIT, firstBowl);
        }
        return new Hit(firstBowl);
    }

    @Override
    public String printResult() {
        return getFirstBowl().toString();
    }
}