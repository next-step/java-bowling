package bowling.domain;

import bowling.domain.rollresult.RollResultType;

import java.util.Objects;

import static java.lang.Math.abs;

public class Score {
    private static final int INIT_NUM = 0;
    private static final int ADD_NUM_UPPER_BOUND = 2;
    private static final int SPARE_BOUND = 1;
    private static final int STRIKE_BOUND = 2;
    private static final int DEFAULT_MAX_SCORE = 10;
    private static final int DEFAULT_MIN_SCORE = 0;
    private static final String INVALID_SCORE = "점수는 0 이상이어야합니다.";
    private static final String INVALID_ADD_NUM = "점수 추가합산은 최대 2회까지 가능합니다.";
    private final int score;
    private final int left;

    public Score() {
        score = INIT_NUM;
        left = INIT_NUM;
    }

    private Score(int score) {
        this.score = score;
        this.left = INIT_NUM;
    }

    private Score(int score, int addNum) {
        this.score = score;
        this.left = addNum;
    }

    public static Score of() {
        return new Score();
    }

    public static Score of(int score) {
        validScore(score);
        return new Score(score);
    }

    public static Score of(int score, int addNum) {
        validScore(score);
        validAddNum(addNum);
        return new Score(score, addNum);
    }

    public Score add(RollResultType result, int nextScore) {
        if ((result.isSpare() && left < SPARE_BOUND)
                || (result.isStrike() && left < STRIKE_BOUND)) {
            return addNext(nextScore);
        }
        return this;
    }

    public boolean isFinished(RollResultType result) {
        return (!result.isStrike() && !result.isSpare())
                || (result.isSpare() && left == SPARE_BOUND)
                || (result.isStrike() && left == STRIKE_BOUND);
    }

    public Score add(Score score) {
        return of(this.score + score.score, this.left);
    }

    public Score add(int score) {
        return of(this.score + score, this.left);
    }

    public Score diff(int score) {
        return of(abs(this.score - score), this.left);
    }

    public int compareTo(int score) {
        return this.score - score;
    }

    public boolean isStrike() {
        return score == DEFAULT_MAX_SCORE;
    }

    public boolean isGutter() {
        return score == DEFAULT_MIN_SCORE;
    }

    public boolean isClear() {
        return score >= DEFAULT_MAX_SCORE;
    }

    private static void validAddNum(int addNum) {
        if (addNum > ADD_NUM_UPPER_BOUND) {
            throw new IllegalStateException(INVALID_ADD_NUM);
        }
    }

    private static void validScore(int score) {
        if (score < INIT_NUM) {
            throw new IllegalArgumentException(INVALID_SCORE);
        }
    }

    private Score addNext(int score) {
        return of(this.score + score, this.left + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score && left == score1.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, left);
    }

    @Override
    public String toString() {
        return "" + score + "";
    }
}
