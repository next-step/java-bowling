package bowling.domain;

import java.util.Objects;

public class Score implements Comparable<Score> {
    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 10;
    private final int score;

    public Score(int score) {
        if (score < MIN_SCORE || score > MAX_SCORE) {
            throw new IllegalArgumentException("유효하지 않은 투구 값입니다.");
        }
        this.score = score;
    }

    public Score sum(Score score) {
        return new Score(this.score + score.score);
    }

    public boolean isGutter() {
        return score == MIN_SCORE;
    }

    public boolean isStrike() {
        return score == MAX_SCORE;
    }

    public int getScore() {
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
    public int compareTo(Score target) {
        return Integer.compare(this.score, target.score);
    }
}
