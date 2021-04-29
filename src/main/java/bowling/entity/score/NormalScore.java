package bowling.entity.score;

import bowling.entity.Pin;

import java.util.Objects;

public class NormalScore implements ScoreType {
    private final Pin pin;

    public NormalScore(Pin score) {
        this.pin = score;
    }

    @Override
    public Pin score() {
        return pin;
    }

    @Override
    public String scoreResult() {
        int pinValue = pin.pin();

        if (pinValue == 0) {
            return "-";
        }

        return String.valueOf(pinValue);
    }

    @Override
    public boolean isFrameEnd() {
        return false;
    }

    @Override
    public ScoreType pinResult(Pin fallenPin) {
        if (pin.isSpare(fallenPin)) {
            return new Spare(pin, fallenPin);
        }

        return new Miss(pin, fallenPin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalScore that = (NormalScore) o;
        return Objects.equals(pin, that.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin);
    }
}
