package bowling.domain.score;

import java.util.Objects;

public class Score {

    public static final Score MAX_SCORE = new Score(10);
    private static final Score MIN_SCORE = new Score(0);

    private final int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score of(int score) {
        validate(score);
        return new Score(score);
    }

    private static void validate(int score) {
        if (score < MIN_SCORE.score || score > MAX_SCORE.score) {
            throw new IllegalArgumentException("점수가 유효범위 내에 있지 않습니다 : " + score);
        }
    }

    public Score add(Score another) {
        return new Score(score + another.score);
    }

    public boolean isGreaterThan(Score another) {
        return score > another.score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
