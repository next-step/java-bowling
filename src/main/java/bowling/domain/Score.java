package bowling.domain;

import java.util.Objects;

public class Score {
    private final int score;

    public Score(int score) {
        this.score = score;
    }

    public Score sum(int point) {
        return new Score(score + point);
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
                "score=" + score +
                '}';
    }
}
