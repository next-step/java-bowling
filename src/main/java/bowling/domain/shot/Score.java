package bowling.domain.shot;

import java.util.Objects;

public class Score {
    private static final int MAX = 10;
    private static final int MIN = 0;

    private final int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score of(int score) {
        if (score < MIN || MAX < score) {
            throw new IllegalArgumentException(String.format("create ShotScore fail, score must be %d~%d : score = %d", MIN, MAX, score));
        }

        return new Score(score);
    }

    public boolean isMax() {
        return this.score == MAX;
    }

    public boolean isMin() {
        return this.score == MIN;
    }

    public Score getLeftScore() {
        return Score.of(MAX - score);
    }

    public boolean range(int includeMin, int excludeMax) {
        return includeMin <= score && score < excludeMax;
    }

    public int score() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    @Override
    public String toString() {
        return "Score{" +
                score +
                '}';
    }
}
