package bowling.domain.state;

import java.util.Objects;

public class NextReady implements State{
    private int fallenPins;

    public NextReady(int fallenPins) {
        this.fallenPins = fallenPins;
    }

    @Override
    public State bowl(int pins) {
        if (fallenPins + pins == 10) {
            return new Spare();
        }
        return null;
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
