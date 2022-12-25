package bowling.model.state;

import bowling.model.Pin;
import bowling.model.Score;

public class First extends Running {

    private final Pin firstPin;

    public First(Pin firstPin) {
        this.firstPin = firstPin;
    }

    @Override
    public State bowl(Pin secondPin) {
        if (this.firstPin.isClearAll(secondPin)) {
            return new Spare(this.firstPin, secondPin);
        }
        return new Miss(this.firstPin, secondPin);
    }

    @Override
    public Score addBonusScore(Score score) {
        return firstPin.sumScore(score);
    }

    @Override
    public String toString() {
        return String.valueOf(firstPin.getValue());
    }
}
