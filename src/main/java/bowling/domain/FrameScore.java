package bowling.domain;

import java.util.Objects;

import bowling.engin.Score;

public class FrameScore implements Score {
    private static final int GUTTER_SCORE = ShotResult.GUTTER.toInt();
    private static final int TURKEY_SCORE = 30;

    private final int score;

    private FrameScore(int score) {
        this.score = score;
    }

    public static FrameScore of(int score) {
        if (score < GUTTER_SCORE || score > TURKEY_SCORE) {
            throw new IllegalArgumentException("a score of one frame must be 0 to 30");
        }

        return new FrameScore(score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameScore that = (FrameScore) o;
        return score == that.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    @Override
    public int toInt() {
        return score;
    }
}
