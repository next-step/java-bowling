package bowling.domain.state;

import bowling.domain.frame.Score;

import java.util.Objects;

public class NextReady implements State {
    private static final int MAX_FALLEN_PINS = 10;

    private int fallenPins;

    public NextReady(int fallenPins) {
        this.fallenPins = fallenPins;
    }

    @Override
    public State bowl(int pins) {
        if (fallenPins + pins == MAX_FALLEN_PINS) {
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
        return String.valueOf(fallenPins);
    }

    @Override
    public Score getScore() {
        return null;
    }

    @Override
    public Score calculateByBeforeScore(Score before) {
        before = before.bowl(this.fallenPins);
        return before;
    }

    @Override
    public void renewScore(Score score) {
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
