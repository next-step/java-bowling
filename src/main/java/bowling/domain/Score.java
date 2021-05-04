package bowling.domain;

import bowling.domain.rollresult.RollResultType;

import java.util.Objects;

import static java.lang.Math.abs;

public class Score {
    private static final int INIT_NUM = 0;
    private static final int ADD_NUM_UPPER_BOUND = 2;
    private static final int SPARE_BOUND = 1;
    private static final int STRIKE_BOUND = 2;
    private static final String INVALID_SCORE = "점수는 0 이상이어야합니다.";
    private static final String INVALID_ADD_NUM = "점수 추가합산은 최대 2회까지 가능합니다.";
    private final int score;
    private final int addNum;

    public Score() {
        score = INIT_NUM;
        addNum = INIT_NUM;
    }

    private Score(int score) {
        this.score = score;
        this.addNum = INIT_NUM;
    }

    private Score(int score, int addNum) {
        this.score = score;
        this.addNum = addNum;
    }

    public static Score of() {
        return new Score();
    }

    public static Score of(int score){
        validScore(score);
        return new Score(score);
    }

    public static Score of(int score, int addNum){
        validScore(score);
        validAddNum(addNum);
        return new Score(score, addNum);
    }

    public Score add(RollResultType result, int nextScore) {
        if(result.isSpare() && addNum < SPARE_BOUND) {
            return addNext(nextScore);
        }
        if(result.isStrike() && addNum < STRIKE_BOUND) {
            return addNext(nextScore);
        }
        return this;
    }

    public Score add(Score score) {
        return of(this.score + score.score, this.addNum);
    }

    public Score add(int score) {
        return of(this.score + score, this.addNum);
    }

    public Score diff(int score) {
        return of(abs(this.score - score), this.addNum);
    }

    public int compareTo(int score) {
        return this.score - score;
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
        return of(this.score + score, this.addNum + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score && addNum == score1.addNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, addNum);
    }

    @Override
    public String toString() {
        return "" + score + "";
    }
}
