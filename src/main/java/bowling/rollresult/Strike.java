package bowling.rollresult;

import java.util.Objects;

public class Strike implements RollResultType {
    private static final String INVALID_SCORE = "스트라이크의 값은 10 이상이어야합니다.";
    private final int score;

    private Strike() {
        score = DEFAULT_MAX_SCORE;
    }

    private Strike(int score) {
        this.score = score;
    }

    public static Strike of() {
        return new Strike();
    }

    public static Strike of(int score) {
        valid(score);
        return new Strike(score);
    }

    private static void valid(int score) {
        if (score < DEFAULT_MAX_SCORE) {
            throw new IllegalArgumentException(INVALID_SCORE);
        }
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
    public int eval() {
        return score;
    }

    @Override
    public RollResultType next(int nextScore) {
        return of(score + nextScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Strike strike = (Strike) o;
        return score == strike.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
