package bowling.entity.score;

import bowling.entity.Pin;

import java.util.Objects;

import static bowling.entity.Pin.MAX_PIN_COUNT;

public class NormalScore implements ScoreType {
    private final Pin pin;

    public NormalScore(Pin pin) {
        this.pin = pin;
    }

    @Override
    public String scoreResult() {
        return String.valueOf(pin.pin());
    }

    @Override
    public boolean isFrameEnd() {
        return false;
    }

    @Override
    public ScoreType bowl(Pin fallenPin) {
        if (pin.sumPin(fallenPin) == MAX_PIN_COUNT) {
            return new Spare(pin);
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
