package bowling.domain.state;

import java.util.Objects;

public class NextReady extends Playing {
    private Pin fallenPins;

    public NextReady(Pin fallenPins) {
        this.fallenPins = fallenPins;
    }

    @Override
    public State bowl(Pin pins) {
        if (fallenPins.isSpare(pins)) {
            return new Spare(fallenPins, pins);
        }
        return new Miss(fallenPins, pins);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public String display() {
        return String.valueOf(fallenPins.getFallenPins());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NextReady nextReady = (NextReady) o;
        return fallenPins == nextReady.fallenPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallenPins);
    }
}
