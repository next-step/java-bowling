package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.Score;

public class Miss extends Finished {

    private final Pin firstPin;
    private final Pin secondPin;

    public Miss(int firstPin, int secondPin) {
        this(Pin.of(firstPin), Pin.of(secondPin));
    }

    Miss(Pin firstPin, Pin secondPin) {
        if (!firstPin.isMissWith(secondPin)) {
            throw new IllegalArgumentException("invalid pair of pins");
        }
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public Score score() {
        return Score.ofMiss(firstPin.sum(secondPin));
    }

    @Override
    public Score additionalScore(Score beforeScore) {
        Score afterScore = beforeScore.addedScore(firstPin);
        if (afterScore.canGetScore()) {
            return afterScore;
        }
        return afterScore.addedScore(secondPin);
    }

    @Override
    public String expression() {
        return firstPin.no() + "|" + secondPin.no();
    }
}
