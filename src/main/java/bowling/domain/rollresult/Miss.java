package bowling.domain.rollresult;

import java.util.Objects;

public class Miss implements RollResultType{

    private final int score = DEFAULT_MIN_SCORE;

    public static Miss of() {
        return new Miss();
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public int eval() {
        return DEFAULT_MIN_SCORE;
    }

    @Override
    public RollResultType next(int nextScore) {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miss miss = (Miss) o;
        return score == miss.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
