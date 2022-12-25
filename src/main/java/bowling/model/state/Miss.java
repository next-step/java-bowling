package bowling.model.state;

import bowling.model.Pin;
import bowling.model.Score;

public class Miss extends Finished {

    private final Pin firstPin;
    private final Pin secondPin;

    public Miss(Pin firstPin, Pin secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public Score getScore() {
        return new Score(firstPin.sum(secondPin), 0);
    }

    @Override
    public Score addBonusScore(Score score) {
        score = firstPin.sumScore(score);
        if (score.canCalculate()) {
            return score;
        }
        return secondPin.sumScore(score);
    }

    @Override
    public String toString() {
        return firstPin.getDesc(secondPin);
    }
}
