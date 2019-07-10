package bowling.domain;

import java.util.Objects;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-10 16:01
 */
class Score {
    private static final int SCORE_RANGE_MIN = 0;
    private static final int SCORE_RANGE_MAX = 10;
    private static final int CHECK_STRIKE_SCORE = 10;
    private static final String SCORE_RANGE_EXCEPTION_MESSAGE = "0 ~ 10 사이만 가능합니다.";

    private final int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score of(int score) {
        if (score > SCORE_RANGE_MAX || score < SCORE_RANGE_MIN) {
            throw new IllegalArgumentException(SCORE_RANGE_EXCEPTION_MESSAGE);
        }
        return new Score(score);
    }

    boolean isStrike() {
        return score == CHECK_STRIKE_SCORE;
    }

    int getScore() {
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
    public String toString() {
        return "Score{" +
                "score=" + score +
                '}';
    }
}
