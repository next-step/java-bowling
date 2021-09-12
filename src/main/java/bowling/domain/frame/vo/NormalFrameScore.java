package bowling.domain.frame.vo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NormalFrameScore {

    private final int first;

    private final int second;

    private final boolean isFirstTry;

    private NormalFrameScore(int first, int second, boolean isFirstTry) {
        this.first = first;
        this.second = second;
        this.isFirstTry = isFirstTry;
    }

    public static NormalFrameScore of(Integer... scores) {
        return of(Arrays.asList(scores));
    }

    public static NormalFrameScore of(List<Integer> scores) {

        validateIsNotEmpty(scores);
        validateEachScore(scores);

        if (scores.size() == 2) {
            return new NormalFrameScore(scores.get(0), scores.get(1), false);
        }
        return new NormalFrameScore(scores.get(0), 0, true);
    }

    private static void validateIsNotEmpty(List<Integer> scores) {
        if (scores == null || scores.isEmpty()) {
            throw new IllegalArgumentException("프레임의 점수가 비어있습니다.");
        }
    }

    private static void validateEachScore(List<Integer> scores) {
        scores.forEach(score -> {
            if (score < 0 || score > 10) {
                throw new IllegalArgumentException("점수는 0에서 10 사이의 값만을 가져야합니다.");
            }
        });
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public boolean isFirstTry() {
        return isFirstTry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrameScore that = (NormalFrameScore) o;
        return first == that.first && second == that.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
