package step3.state;

import java.util.Objects;
import step3.domain.Pins;
import step3.domain.Score;
import step3.exceptions.CanNotThrowBallException;

public class FirstBowl implements State {
    private Score score;
    private final Pins firstOfPin;

    public FirstBowl(int firstFalledPins) {
        firstOfPin = new Pins(firstFalledPins);
    }

    public State bowl(int secondFalledPins) {
        Pins secondOfPins = new Pins(secondFalledPins);
        if (firstOfPin.isSpair(secondFalledPins)) {
            return new Spair(firstOfPin, secondOfPins);
        }
        return new Miss(firstOfPin, secondOfPins);
    }

    @Override
    public Score score() {
        return score;
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        beforeScore = firstOfPin.sumScore(beforeScore);
        if (beforeScore.canCalculateScore()) {
            return beforeScore;
        }
        throw new CanNotThrowBallException();
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public String symbol() {
        return Integer.toString(firstOfPin.getFallenPins());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FirstBowl firstBowl = (FirstBowl) o;
        return firstOfPin == firstBowl.firstOfPin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstOfPin);
    }
}
