package bowling.entity.score;

import bowling.entity.Pin;

import java.util.Objects;

import static bowling.entity.Pin.*;

public class Spare extends Finish {
    private static final String SPARE_SYMBOL = "/";
    private final Pin firstPin;

    public Spare(int firstPinValue) {
        this.firstPin = new Pin(firstPinValue);
    }

    public Spare(Pin firstPin) {
        this.firstPin = firstPin;
    }

    @Override
    public String scoreResult() {
        return firstPin.pin() + SCORE_ASSOCIATION_SYMBOL + SPARE_SYMBOL;
    }

    @Override
    public ScoreType bowl(Pin fallenPin) {
        if (fallenPin.pin() == MAX_PIN_COUNT) {
            return new Strike();
        }
        return new NormalScore(fallenPin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spare spare = (Spare) o;
        return Objects.equals(firstPin, spare.firstPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin);
    }
}
