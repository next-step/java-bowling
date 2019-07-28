package bowling.domain;

import bowling.domain.state.Spare;
import bowling.domain.state.Strike;

import java.util.Objects;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-28 02:06
 */
public class Score {
    private static final int MISS_STATE_DEFUALT_REMAIN_COUNT = 0;
    private static final int FINISH_CHECK_COUNT = 0;
    private static final int CALCULATE_DECREASE_DEPTH = 1;

    private int score;
    private int remainCount;

    private Score(int score, int remainCount) {
        this.score = score;
        this.remainCount = remainCount;
    }

    public static Score ofStrike() {
        return new Score(Strike.SCORE, Strike.SCORE_BOWL_COUNT);
    }

    public static Score ofSpare() {
        return new Score(Spare.SCORE, Spare.SCORE_BOWL_COUNT);
    }

    public static Score ofMiss(int score) {
        return new Score(score, MISS_STATE_DEFUALT_REMAIN_COUNT);
    }

    public static Score ofRemain(int score, int remainCount) {
        return new Score(score, remainCount);
    }

    public Score calculate(Score addScore) {
        return new Score(this.score + addScore.getScore(), remainCount - CALCULATE_DECREASE_DEPTH);
    }

    public int getScore() {
        return score;
    }

    public boolean remainCalculate() {
        if(remainCount == FINISH_CHECK_COUNT) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score &&
                remainCount == score1.remainCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, remainCount);
    }

    @Override
    public String toString() {
        return "Score{" +
                "score=" + score +
                ", remainCount=" + remainCount +
                '}';
    }
}
