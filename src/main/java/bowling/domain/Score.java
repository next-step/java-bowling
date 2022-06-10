package bowling.domain;

import bowling.exception.InvalidScoreCountException;
import bowling.exception.InvalidScoreException;

import java.util.Objects;

public class Score {

    public static final int MIN_BASE_SCORE = 0;
    public static final int MAX_BASE_SCORE = 10;
    public static final int MIN_ADDITIONAL_COUNT = 0;
    public static final int MAX_ADDITIONAL_COUNT = 2;
    private static final int STRIKE_OR_SPARE_BASE_SCORE = 10;
    private static final int STRIKE_ADDITIONAL_COUNT = 2;
    private static final int SPARE_ADDITIONAL_COUNT = 1;

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

    public static Score strike() {
        return of(STRIKE_OR_SPARE_BASE_SCORE, STRIKE_ADDITIONAL_COUNT);
    }

    public static Score spare() {
        return of(STRIKE_OR_SPARE_BASE_SCORE, SPARE_ADDITIONAL_COUNT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score && additionalScoreCount == score1.additionalScoreCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, additionalScoreCount);
    }
}
