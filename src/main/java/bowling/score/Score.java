package bowling.score;

import bowling.global.exception.InputScoreNullPointerException;
import bowling.global.exception.OutOfScoreRangeException;
import bowling.global.utils.ExceptionMessage;
import bowling.global.utils.StringParser;
import org.apache.logging.log4j.util.Strings;

public class Score {

    private static final int SCORE_MIN_VALUE = 0;
    private static final int SCORE_MAX_VALUE = 10;

    private int score;

    private Score(String score) {
        validateScoreIsNull(score);
        this.score = new StringParser(score).toInt();
        validateOutofScoreRange();
    }

    public static Score from(String score) {
        return new Score(score);
    }

    public boolean isStrike() {
        return this.score == SCORE_MAX_VALUE;
    }

    public boolean isGutter() {
        return this.score == SCORE_MIN_VALUE;
    }

    private void validateScoreIsNull(String score) {
        if (Strings.isBlank(score)) {
            throw new InputScoreNullPointerException(ExceptionMessage.INVALID_PITCH_BALL_IS_NULL);
        }
    }

    private void validateOutofScoreRange() {
        if (score < SCORE_MIN_VALUE || score > SCORE_MAX_VALUE) {
            throw new OutOfScoreRangeException(ExceptionMessage.INVALID_PITCH_RANGE);
        }
    }

    public int getScore() {
        return score;
    }

}
