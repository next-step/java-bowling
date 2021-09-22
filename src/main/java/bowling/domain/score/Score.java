package bowling.domain.score;

import bowling.domain.frame.Frame;
import java.util.Arrays;
import java.util.Objects;

public class Score implements Comparable<Score> {

    private static final int MAX_SCORE = 300;
    private final int score;
    private final boolean endFrame;

    private Score(int score, boolean endFrame) {
        this.endFrame = endFrame;
        if (score > MAX_SCORE) {
            throw new IllegalArgumentException("최대 300점만 얻을 수 있습니다.");
        }

        this.score = score;
    }

    public static Score create() {
        return new Score(0, false);
    }

    public static Score from(int givenLastScore) {
        return new Score(givenLastScore, false);
    }

    public static Score of(int givenLastScore, boolean end) {
        return new Score(givenLastScore, end);
    }

    public Score sumWithScores(Score... other) {
        return from(score + Arrays.stream(other)
            .mapToInt(value -> value.score)
            .sum());
    }

    public Score sumWithFrames(Frame... other) {
        return of(score + Arrays.stream(other)
            .mapToInt(value -> value.numberOfDownedPins().score)
            .sum(), true);
    }

    public boolean checked() {
        return endFrame;
    }

    public int score() {
        return score;
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(this.score, o.score);
    }

    @Override
    public String toString() {
        return "Score{" +
            "score=" + score +
            ", endFrame=" + endFrame +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
