package step3.state;

import step3.domain.Pins;
import step3.domain.Score;

public class Miss extends Finished {
    private Pins firstOfPin;
    private Pins secondOfPin;

    public Miss(Pins firstOfPin, Pins secondOfPin) {
        this.firstOfPin = firstOfPin;
        this.secondOfPin = secondOfPin;
    }

    @Override
    public Score score() {
        return new Score(firstOfPin.getFallenPins() + secondOfPin.getFallenPins(), 0);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        score = firstOfPin.sumScore(score);
        if (score.canCalculateScore()) {
            return score;
        }
        score = secondOfPin.sumScore(score);
        return score;
    }

    @Override
    public String symbol() {
        return firstOfPin.getFallenPins() + "|" + secondOfPin.getFallenPins();
    }
}
