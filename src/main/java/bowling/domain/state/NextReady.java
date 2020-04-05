package bowling.domain.state;

import bowling.domain.frame.Score;

import java.util.Objects;

public class NextReady extends Playing {
    private static final int MAX_FALLEN_PINS = 10;

    private Pin fallenPins;

    public NextReady(Pin fallenPins) {
        this.fallenPins = fallenPins;
    }

    @Override
    public State bowl(Pin pins) {
        if (fallenPins.getFallenPins() + pins.getFallenPins() == MAX_FALLEN_PINS) {
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
    public Score calculateByBeforeScore(Score before) {
        before = before.bowl(fallenPins.getFallenPins());
        return before;
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
