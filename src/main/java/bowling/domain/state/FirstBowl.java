package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.Score;

public class FirstBowl extends Running {

    private final Pin firstPin;

    FirstBowl(int no) {
        this(Pin.of(no));
    }

    FirstBowl(Pin firstPin) {
        if (firstPin.isStrike()) {
            throw new IllegalArgumentException("first bowl pin can't be strike");
        }
        this.firstPin = firstPin;
    }

    @Override
    public State bowl(Pin secondPin) {
        if (firstPin.isSpareWith(secondPin)) {
            return new Spare(firstPin, secondPin);
        }
        return new Miss(firstPin, secondPin);
    }

    @Override
    public Score additionalScore(Score beforeScore) {
        return beforeScore.addedScore(firstPin);
    }

    @Override
    public String expression() {
        return String.valueOf(firstPin.no());
    }
}
