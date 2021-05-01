package bowling.entity.score;

import bowling.entity.Pin;
import bowling.entity.Score;

import java.util.Objects;

import static bowling.entity.Pin.MAX_PIN_COUNT;
import static bowling.entity.Pin.SCORE_ASSOCIATION_SYMBOL;

public class Miss extends Finish {
    private final Pin firstPin;
    private final Pin secondPin;

    public Miss(int firstPinValue, int secondPinValue) {
        Pin firstPin = new Pin(firstPinValue);
        Pin secondPin = new Pin(secondPinValue);

        firstPin.sum(secondPin);
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    public Miss(Pin firstPin, Pin secondPin) {
        firstPin.sum(secondPin);
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public String scoreResult() {
        return firstPin.pin() + SCORE_ASSOCIATION_SYMBOL + secondPin.pin();
    }

    @Override
    public ScoreType bowl(Pin fallenPin) {
        if (fallenPin.pin() == MAX_PIN_COUNT) {
            return new Strike();
        }
        return new NormalScore(fallenPin);
    }

    @Override
    public Score score() {
        return new Score(firstPin.sum(secondPin));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miss miss = (Miss) o;
        return Objects.equals(firstPin, miss.firstPin) && Objects.equals(secondPin, miss.secondPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin, secondPin);
    }
}
