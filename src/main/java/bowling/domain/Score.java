package bowling.domain;

import java.util.Objects;

public class Score {

    protected static final int MAX_SCORE = 10;

    private final int score;

    protected Score(int score) {
        this.score = score;
    }

    public boolean isStrike() {
        return score == MAX_SCORE;
    }

    public static Score from(int score) {
        validateScore(score);
        return new Score(score);
    }

    public int getScore() {
        return score;
    }

    public Score next(int score) {
        validateScore(score);
        validateComibnedScores(score);
        return new Score(score);
    }

    protected static void validateScore(int score) {
        if (outOfRange(score)) {
            throw new IllegalArgumentException("잘못된 점수를 입력하였습니다.");
        }
    }

    protected void validateComibnedScores(int score) {
        if (this.score + score > MAX_SCORE) {
            throw new IllegalArgumentException("1차시도와 2차시도의 합계는 10점을 넘을 수 없습니다.");
        }
    }

    private static boolean outOfRange(int score) {
        return score < 0 || score > MAX_SCORE;
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
}
