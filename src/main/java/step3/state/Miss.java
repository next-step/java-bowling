package step3.state;

import step3.Pins;
import step3.Score;
import step3.exceptions.CanNotThrowBallException;

public class Miss extends Finished {
    private Pins firstOfPin;
    private Pins secondOfPin;

    public Miss(Pins firstOfPin, Pins secondOfPin) {
        this.firstOfPin = firstOfPin;
        this.secondOfPin = secondOfPin;
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        beforeScore = firstOfPin.sumScore(beforeScore);
        if (beforeScore.canCalculateScore()) {
            return beforeScore;
        }
        throw new CanNotThrowBallException();
    }
}
