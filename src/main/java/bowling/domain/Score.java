package bowling.domain;

import java.util.Objects;

public class Score {

    private static final int MAX_SCORE = 10;

    private final int first;

    private final int second;

    protected Score(int first, int second) {
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

    public boolean isSpareOrStrike() {
        return first + second >= MAX_SCORE;
    }

    protected static void validateScore(int score) {
        if (outOfRange(score)) {
            throw new IllegalArgumentException("잘못된 점수를 입력하였습니다.");
        }
    }

    protected void validateComibnedScores(int score) {
        if (first + score > MAX_SCORE) {
            throw new IllegalArgumentException("1차시도와 2차시도의 합계는 10점을 넘을 수 없습니다.");
        }
    }

    protected int getFirst() {
        return first;
    }

    protected int getSecond() {
        return second;
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
