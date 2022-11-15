package bowling.domain.frame;

import java.util.Objects;

public class Score {
    private static final String CANNOT_CALCULATE_SCORE_EXCEPTION_MESSAGE = "아직 스코어를 계산할 수 없는 상태입니다.";

    private static final int UN_SCORE_VALUE = -1;

    private final int score;
    private final int left;

    public static Score needToMoreBowl() {
        return new Score(UN_SCORE_VALUE, UN_SCORE_VALUE);
    }

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public Score addBonusScore(int score) {
        return new Score(this.score + score, left - 1);
    }

    public boolean canCalculateScore() {
        return left == 0;
    }

    public int getValue() {
        if (!canCalculateScore()) {
            throw new IllegalStateException(CANNOT_CALCULATE_SCORE_EXCEPTION_MESSAGE);
        }

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

    @Override
    public String toString() {
        return "Score{" +
            "score=" + score +
            ", left=" + left +
            '}';
    }
}
