package bowling.domain;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-09 12:59
 */
public class Score {
    private static final String LIMIT_EXCEPTION_MESSAGE = "10 이하의 숫자만 가능합니다.";
    private static final int LIMIT_SCORE = 10;
    private final int score;

    public Score(int score) {
        if (score > LIMIT_SCORE) {
            throw new IllegalArgumentException(LIMIT_EXCEPTION_MESSAGE);
        }
        this.score = score;
    }

    public static Score of(int fallCount) {
        return new Score(fallCount);
    }

    public boolean isStrike() {
        return score == LIMIT_SCORE;
    }

    public int checkRemainPinNumber(int maxPinCount) {
        return maxPinCount - score;
    }
}
