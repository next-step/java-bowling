package bowling.domain;

import java.util.Objects;

public class Score {

    private static final int MAX_SCORE = 10;

    private final int first;

    private final int second;

    private Score(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public boolean isStrike() {
        return first == MAX_SCORE;
    }

    public static Score first(int score) {
        validateScore(score);
        return new Score(score, 0);
    }

    public Score withSecond(int score) {
        validateScore(score);
        validateComibnedScores(score);
        return new Score(first, score);
    }

    private static void validateScore(int score) {
        if (outOfRange(score)) {
            throw new IllegalArgumentException("잘못된 점수를 입력하였습니다.");
        }
    }

    private void validateComibnedScores(int score) {
        if (first + score > MAX_SCORE) {
            throw new IllegalArgumentException("한 프레임의 합계는 10점을 넘을 수 없습니다.");
        }
    }

    private static boolean outOfRange(int score) {
        return score < 0 || score > MAX_SCORE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return first == score.first && second == score.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
