package bowling.domain.state;

import bowling.domain.Score;

import java.util.Objects;

public class FirstPitch implements State {
    private Pins pins = new Pins();

    @Override
    public State bowl(int count) {
        pins.fall(count);
        if (pins.isAllDown()) {
            return new Strike(pins);
        }

        return new SecondPitch(pins);
    }

    @Override
    public Pins getPins() {
        return pins;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Score getScore() {
        return new Score();
    }

    @Override
    public Score addNextScore(Score score) {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstPitch that = (FirstPitch) o;
        return Objects.equals(pins, that.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
