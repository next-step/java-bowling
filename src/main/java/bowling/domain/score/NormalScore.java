package bowling.domain.score;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class NormalScore extends BaseScore {

    private final boolean isDone;

    private NormalScore(int first, int second, boolean isDone) {
        super(first, second);
        this.isDone = isDone;
    }


    public static NormalScore first(int score) {
        validateScore(score);
        return new NormalScore(score, -1, false);
    }

    public NormalScore second(int score) {
        validateScore(score);
        validateCombinedScores(score);
        return new NormalScore(getFirst(), score, true);
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    protected void validateCombinedScores(int score) {
        if (getFirst() + score > MAX) {
            throw new IllegalArgumentException("1차시도와 2차시도의 합계는 10점을 넘을 수 없습니다.");
        }
    }

    @Override
    public List<Integer> getAll() {
        return Collections.unmodifiableList(Arrays.asList(getFirst(), getSecond()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalScore that = (NormalScore) o;
        return isDone == that.isDone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isDone);
    }
}
