package bowling.domain.score;

import java.util.Objects;

public class Score {
    private static final int MAX_PIN_COUNT = 10;
    private static final int MIN_PIN_COUNT = 0;
    private static final int MIN_LEFT_COUNT = 0;
    private static final int PROGRESS_VALUE = -1;

    private final int score;
    private final int left;

    private Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public static Score of(int score) {
        return Score.of(score, MIN_LEFT_COUNT);
    }

    public static Score of(int score, int left) {
        return new Score(score, left);
    }

    public static Score ofStrike() {
        return Score.of(MAX_PIN_COUNT, 2);
    }

    public static Score ofSpare() {
        return Score.of(MAX_PIN_COUNT, 1);
    }

    public static Score init() {
        return Score.of(MIN_PIN_COUNT, MIN_LEFT_COUNT);
    }

    public static Score ofProgress() {
        return Score.of(PROGRESS_VALUE, PROGRESS_VALUE);
    }

    public Score add(Score score) {
        return new Score(this.score + score.score, left + score.left);
    }

    public Score accumulate(int score) {
        return new Score(this.score + score, left - 1);
    }

    public boolean isAccumulateDone() {
        return this.left == MIN_LEFT_COUNT;
    }

    public boolean isProgressState() {
        return this.equals(ofProgress());
    }

    public int left() {
        return left;
    }

    public int score() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Score score1 = (Score)o;
        return score == score1.score && left == score1.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, left);
    }
}
