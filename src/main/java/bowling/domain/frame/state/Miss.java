package bowling.domain.frame.state;

import java.util.List;

import bowling.domain.frame.Score;

public class Miss extends Finished {
    private static final String INVALID_PINS_EXCEPTION_MESSAGE = "Miss의 조건을 만족하지 않습니다.";

    private final Pins firstPins;
    private final Pins secondPins;

    public Miss(Pins firstPins, Pins secondPins) {
        validate(firstPins, secondPins);
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public Score createScore() {
        return new Score(List.of(firstPins, secondPins));
    }

    @Override
    public boolean canBonusBowl() {
        return false;
    }

    private void validate(Pins firstPins, Pins secondPins) {
        if (firstPins.isStrike() || firstPins.isSpare(secondPins)) {
            throw new IllegalArgumentException(INVALID_PINS_EXCEPTION_MESSAGE);
        }
    }
}
