package bowling.domain.frame;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NormalFrameScore {

    private final int first;

    private final int second;

    private NormalFrameScore(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public static NormalFrameScore of(Integer... scores) {
        return of(Arrays.asList(scores));
    }

    public static NormalFrameScore of(List<Integer> scores) {
        validate(scores);
        return new NormalFrameScore(scores.get(0), scores.get(1));
    }

    private static void validate(List<Integer> scores) {
        validateIsNotEmpty(scores);
        validateHasTwoElements(scores);
        validateEachScore(scores);
    }

    private static void validateEachScore(List<Integer> scores) {
        scores.forEach(score -> {
            if (score < 0 || score > 10) {
                throw new IllegalArgumentException("점수는 0에서 10 사이의 값만을 가져야합니다.");
            }
        });
    }

    private static void validateHasTwoElements(List<Integer> scores) {
        if (scores.size() != 2) {
            throw new IllegalArgumentException("프레임의 점수는 2개여야 합니다.");
        }
    }

    private static void validateIsNotEmpty(List<Integer> scores) {
        if (scores == null || scores.isEmpty()) {
            throw new IllegalArgumentException("프레임의 점수가 비어있습니다.");
        }
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
