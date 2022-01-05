package bowling.domain;

import java.util.Objects;

import bowling.engine.Score;

public class BowlingScore implements Score {
    public static final Score ACCUMULATION_BASE = BowlingScore.of(0);

    private final int score;

    private BowlingScore(int score) {
        this.score = score;
    }

    public static BowlingScore of(int score) {
        if (score < GUTTER_SCORE || score > TURKEY_SCORE) {
            throw new IllegalArgumentException("a score of one frame must be 0 to 30");
        }

        return new BowlingScore(score);
    }

    @Override
    public Score add(Score other) {
        return of(score + other.toInt());
    }

    @Override
    public int toInt() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingScore that = (BowlingScore) o;
        return score == that.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    @Override
    public String toString() {
        return "BowlingScore{" +
                "score=" + score +
                '}';
    }
}
