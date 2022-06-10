package bowling.domain;

import bowling.exception.InvalidScoreCountException;
import bowling.exception.InvalidScoreException;

public class Score {

    public static final int MIN_BASE_SCORE = 0;
    public static final int MAX_BASE_SCORE = 10;
    public static final int MIN_ADDITIONAL_COUNT = 0;
    public static final int MAX_ADDITIONAL_COUNT = 2;

    private int score;
    private int additionalScoreCount;

    private Score(int score, int additionalScoreCount) {
        if (score > MAX_BASE_SCORE || score < MIN_BASE_SCORE) {
            throw new InvalidScoreException(score);
        }
        if (additionalScoreCount < MIN_ADDITIONAL_COUNT || additionalScoreCount > MAX_ADDITIONAL_COUNT) {
            throw new InvalidScoreCountException(additionalScoreCount);
        }
        this.score = score;
        this.additionalScoreCount = additionalScoreCount;
    }

    public static Score of(int score, int additionalScoreCount) {
        return new Score(score, additionalScoreCount);
    }

}
