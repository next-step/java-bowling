package bowling.domain.state;

import bowling.domain.Score;

import java.util.Objects;

public class Strike extends Finished {
    private static final String INVALID_SCORE = "스트라이크의 값은 10 이상이어야합니다.";
    private final State hit;

    private Strike(State hit) {
        this.hit = hit;
    }

    public static Strike of() {
        return new Strike(OneHit.ofOne(Score.ofStrike()));
    }

    public static Strike of(State hit) {
        valid(hit.eval());
        return new Strike(hit);
    }

    @Override
    public boolean isStrike() {
        return true;
    }

    @Override
    public boolean canAccumulate() {
        return !hit.eval().isFinished();
    }

    @Override
    public Score eval() {
        return hit.eval();
    }

    @Override
    public State next(int nextScore) {
        return of(OneHit.ofOne(hit.eval().add(nextScore)));
    }

    private static void valid(Score score) {
        if (!score.isClear()) {
            throw new IllegalArgumentException(INVALID_SCORE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Strike strike = (Strike) o;
        return Objects.equals(hit, strike.hit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hit);
    }

    @Override
    public String toString() {
        return "X";
    }
}
