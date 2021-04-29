package bowling.entity.score;

import bowling.entity.Pin;

import java.util.Objects;

public class Strike implements ScoreType {

    private final Pin score;

    public Strike(Pin score) {
        this.score = score;
    }

    @Override
    public Pin score() {
        return score;
    }

    @Override
    public String scoreResult() {
        return "X";
    }

    @Override
    public boolean isFrameEnd() {
        return true;
    }

    @Override
    public ScoreType pinResult(Pin fallenPin) {
        if (fallenPin.isStrike()) {
            return new Strike(fallenPin);
        }

        return new NormalScore(fallenPin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Strike strike = (Strike) o;
        return Objects.equals(score, strike.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
