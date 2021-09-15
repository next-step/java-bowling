package bowling.domain.score;

import java.util.Objects;

public class NormalScore extends Score {

    private final boolean isDone;

    private NormalScore(int score, boolean isDone) {
        super(score, 0);
        this.isDone = isDone;
    }

    private NormalScore(int first, int second, boolean isDone) {
        super(first, second);
        this.isDone = isDone;
    }

    public static NormalScore start() {
        return new NormalScore(0, 0, false);
    }

    public static NormalScore first(int score) {
        validateScore(score);
        return new NormalScore(score, false);
    }

    public NormalScore second(int score) {
        validateScore(score);
        validateCombinedScores(score);
        return new NormalScore(this.first, score, true);
    }

    public static NormalScore none() {
        return new NormalScore(0, 0, true);
    }

    public static boolean isNone(NormalScore score) {
        return none().equals(score);
    }

    public boolean isDone() {
        return isDone;
    }


    @Override
    protected void validateCombinedScores(int score) {
        if (this.first + score > MAX_SCORE) {
            throw new IllegalArgumentException("1차시도와 2차시도의 합계는 10점을 넘을 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalScore that = (NormalScore) o;
        return first == that.first && second == that.second && isDone == that.isDone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, isDone);
    }
}
