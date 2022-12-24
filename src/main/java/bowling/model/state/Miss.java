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
    public Score addBonusScore(Score beforeScore) {
        Score score = beforeScore.bowl(firstPin.getValue());
        if (score.canCalculate()) {
            return score;
        }
        return score.bowl(secondPin.getValue());
    }

    @Override
    public String toString() {
        return firstPin.getValue() + "|" + secondPin.getValue();
    }
}
