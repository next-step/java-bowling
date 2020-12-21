package bowling.domain.state;

import bowling.domain.Score;

import java.util.Objects;

public class SecondPitch implements State {
    private Pins pins;

    public SecondPitch(Pins pins) {
        this.pins = pins;
    }

    @Override
    public State bowl(int count) {
        pins.fall(count);
        if (pins.isAllDown()) {
            return new Spare(pins);
        }

        return new Miss(pins, pins.getFirstFall() + pins.getSecondFall());
    }

    @Override
    public Pins getPins() {
        return pins;
    }

    @Override
    public Score getScore() {
        return new Score();
    }

    @Override
    public Score addNextScore(Score score) {
        if (!score.isFinished()) {
            score = score.pitch(pins.getFirstFall());
        }
        return score;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecondPitch that = (SecondPitch) o;
        return Objects.equals(pins, that.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
