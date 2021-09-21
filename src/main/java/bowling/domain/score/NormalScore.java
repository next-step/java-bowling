package bowling.domain.score;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class NormalScore extends Score {

    private final boolean isDone;

    private NormalScore(int score, boolean isDone) {
        super(score, -1);
        this.isDone = isDone;
    }

    private NormalScore(int first, int second, boolean isDone) {
        super(first, second);
        this.isDone = isDone;
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

    public boolean isDone() {
        return isDone;
    }

    public int sum() {
        if (isStrike()) {
            return MAX;
        }
        return first + second;
    }

    @Override
    protected void validateCombinedScores(int score) {
        if (this.first + score > MAX) {
            throw new IllegalArgumentException("1차시도와 2차시도의 합계는 10점을 넘을 수 없습니다.");
        }
    }

    @Override
    public List<Integer> getAll() {
        return Collections.unmodifiableList(Arrays.asList(first, second));
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
