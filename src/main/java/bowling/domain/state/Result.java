package bowling.domain.state;

import bowling.domain.Score;

import java.util.Objects;

public abstract class Result implements State {
    protected Score score;
    protected Pins pins;

    public Result(Pins pins) {
        this.pins = pins;
    }

    @Override
    public State bowl(int count) {
        return this;
    }

    @Override
    public Pins getPins() {
        return pins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(pins, result.pins);
    }

    @Override
    public Score getScore() {
        return score;
    }

    public Score addNextScore(Score score, int pins) {
        if (!score.isFinished()) {
            score = score.pitch(pins);
        }
        return score;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
