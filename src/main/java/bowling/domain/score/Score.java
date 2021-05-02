package bowling.domain.score;

import java.util.Objects;

public class Score {
    private static final int MIN_LEFT_COUNT = 0;

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

    public Score add(Score score) {
        return new Score(this.score + score.score, left + score.left);
    }

    public Score accumulate(int score) {
        return new Score(this.score + score, left - 1);
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
