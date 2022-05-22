package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.Score;

public class Spare extends Finished {

    private final Pin firstPin;
    private final Pin secondPin;

    public Spare(int firstPin, int secondPin) {
        this(Pin.of(firstPin), Pin.of(secondPin));
    }

    public Spare(Pin firstPin, Pin secondPin) {
        if (!firstPin.isSpareWith(secondPin)) {
            throw new IllegalArgumentException("invalid pin for spare");
        }
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public Score score() {
        return Score.ofSpare();
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
        return firstPin.no() + "|/";
    }
}
