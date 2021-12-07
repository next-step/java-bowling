package bowling.domain;

import java.util.Objects;

public class Score {
    private final int MIN = 0;
    private final int MAX = 30;

    private final int score;

    public Score(int score) {
        this.score = validateScore(score);
    }

    private int validateScore(int score) {
        if (score < MIN || score > MAX) {
            throw new IllegalArgumentException("Score must be between 0 and 30");
        }

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
