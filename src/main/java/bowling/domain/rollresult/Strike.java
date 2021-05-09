package bowling.domain.rollresult;

import bowling.domain.Score;

import java.util.Objects;

public class Strike extends RollResultType {
    private static final String INVALID_SCORE = "스트라이크의 값은 10 이상이어야합니다.";
    private final RollResultType hit;

    private Strike(RollResultType hit) {
        this.hit = hit;
    }

    public static Strike of() {
        return new Strike(OneHit.ofOne(Score.ofStrike()));
    }

    public static Strike of(RollResultType hit) {
        valid(hit.eval());
        return new Strike(hit);
    }

    @Override
    public boolean isStrike() {
        return true;
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
    public boolean canAccumulate() {
        return !hit.eval().isFinished();
    }

    @Override
    public Score eval() {
        return hit.eval();
    }

    @Override
    public RollResultType next(int nextScore) {
        return of(OneHit.of(hit.eval().add(nextScore)));
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
