package bowling.domain.frame.vo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FinalFrameScore {

    private final int first;

    private final int second;

    private final int third;

    private final int trial;

    private FinalFrameScore(int first, int second, int third, int trial) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.trial = trial;
    }

    public static FinalFrameScore of(Integer... scores) {
        return of(Arrays.asList(scores));
    }

    public static FinalFrameScore of(List<Integer> scores) {

        validateIsNotEmpty(scores);
        validateEachScore(scores);

        if (scores.size() == 1) {
            return new FinalFrameScore(scores.get(0), 0, 0, 1);
        }

        if (scores.size() == 2) {
            return new FinalFrameScore(scores.get(0), scores.get(1), 0, 2);
        }

        return new FinalFrameScore(scores.get(0), scores.get(1), scores.get(2), 3);
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

    public int getThird() {
        return third;
    }

    public int getTrial() {
        return trial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrameScore that = (FinalFrameScore) o;
        return first == that.first && second == that.second && third == that.third;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, third);
    }
}
