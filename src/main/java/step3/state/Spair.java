package step3.state;

import step3.domain.Pins;
import step3.domain.Score;
import step3.exceptions.CanNotThrowBallException;
import step3.exceptions.PinNumberExecption;

public class Spair extends Finished {
    private Score score;
    private Pins firstOfPin;
    private Pins secondOfPin;

    public Spair(Pins firstOfPin, Pins secondOfPin) {
        if (firstOfPin.getFallenPins() + secondOfPin.getFallenPins() > 10) {
            throw new PinNumberExecption();
        }
        this.score = new Score(10, 1);
        this.firstOfPin = firstOfPin;
        this.secondOfPin = secondOfPin;
    }

    @Override
    public Score score() {
        return score;
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
        return firstOfPin.getFallenPins() + "|/";
    }
}
