package bowling.domain;

import java.util.Objects;

public class Score {
    private static final int MIN = 0;
    private static final int MAX = 30;

    private final int score;

    public Score() {
        this.score = MIN;
    }

    public Score(int score) {
        this.score = validateScore(score);
    }

    private int validateScore(int score) {
        if (score < MIN || score > MAX) {
            throw new IllegalArgumentException("Score must be between 0 and 30");
        }

        return score;
    }

    public int getScore() {
        return score;
    }

    public Score add(Score other) {
        return new Score(score + other.score);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Score other = (Score) object;

        return score == other.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
