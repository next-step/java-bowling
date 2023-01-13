package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.Objects;

public class FirstPin extends Running {

    private final Pin firstPin;

    public FirstPin(Pin firstPin) {
        this.firstPin = firstPin;
    }

    @Override
    public Status bowl(Pin secondPin) {
        if (firstPin.isClear(secondPin)) {
            return new Spare(firstPin);
        }
        return new Miss(firstPin, secondPin);
    }

    @Override
    public Score calculateScore(Score lastScore) {
        return lastScore.bowl(firstPin.amount());
    }

    @Override
    public String toString() {
        return firstPin.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        return getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }
}
