package bowling.entity.score;

import bowling.entity.Pin;

import java.util.Objects;

public class Miss implements ScoreType {
    private final Pin firstPin;
    private final Pin secondPin;

    public Miss(Pin firstPin, Pin secondPin) {
        firstPin.sumPin(secondPin);
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public Pin score() {
        return secondPin;
    }

    @Override
    public String scoreResult() {
        return firstPin.miss(secondPin);
    }

    @Override
    public boolean isFrameEnd() {
        return true;
    }

    @Override
    public ScoreType pinResult(Pin fallenPin) {
        return new None();
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
