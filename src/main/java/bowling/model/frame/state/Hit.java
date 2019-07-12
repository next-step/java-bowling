package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;

import static bowling.model.Pins.MAX;
import static bowling.model.Pins.MIN;

public class Hit extends FirstState {

    private static final String ERROR_MESSAGE = "핀은 %s ~ %s 사이 값이어야 합니다";
    static final int MIN_COUNT_OF_PINS_IN_HIT = MIN + 1;
    static final int MAX_COUNT_OF_PINS_IN_HIT = MAX - 1;

    private Hit(Pins firstBowl) {
        super(firstBowl);
    }

    static State valueOf(Pins firstBowl) {
        if (Gutter.isMatch(firstBowl) || Strike.isMatch(firstBowl)) {
            throw new IllegalArgumentException(String.format(ERROR_MESSAGE, MIN_COUNT_OF_PINS_IN_HIT, MAX_COUNT_OF_PINS_IN_HIT));
        }
        return new Hit(firstBowl);
    }

    @Override
    public String printResult() {
        return firstBowl.toString();
    }
}