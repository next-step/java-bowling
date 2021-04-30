package bowling.entity.score;

import bowling.entity.Pin;

import java.util.Objects;

public class Spare implements ScoreType {
    private final Pin firstPin;

    public Spare(Pin firstPin) {
        this.firstPin = firstPin;
    }

    @Override
    public String scoreResult() {
        return firstPin.spare();
    }

    @Override
    public boolean isFrameEnd() {
        return true;
    }

    @Override
    public ScoreType pinResult(Pin fallenPin) {
        if (fallenPin.isStrike()) {
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
