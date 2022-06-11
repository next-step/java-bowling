package bowling.domain;

import bowling.exception.CannotCalculateScore;
import bowling.exception.InvalidScoreCountException;
import bowling.exception.InvalidScoreException;

import java.util.Objects;

public class Score {

    public static final int MIN_SCORE = 0;
    public static final int MIN_ADDITIONAL_COUNT = 0;
    public static final int MAX_ADDITIONAL_COUNT = 2;
    private static final int UNIT_ADDITIONAL_COUNT = 1;
    private static final int STRIKE_OR_SPARE_BASE_SCORE = 10;
    private static final int STRIKE_ADDITIONAL_COUNT = 2;
    private static final int SPARE_ADDITIONAL_COUNT = 1;
    private static final int MISS_ADDITIONAL_COUNT = 0;

    private final int score;
    private final int additionalScoreCount;

    private Score(int score, int additionalScoreCount) {
        if (score < MIN_SCORE) {
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

    public static Score miss(Hit firstHit, Hit secondHit) {
        return of(firstHit.sum(secondHit).toInt(), MISS_ADDITIONAL_COUNT);
    }

    public static Score bonus(Hit bonusHit) {
        return of(bonusHit.toInt(), MISS_ADDITIONAL_COUNT);
    }

    public boolean hasAdditionalScoreCount() {
        return additionalScoreCount > MIN_ADDITIONAL_COUNT;
    }

    public Score addAdditionalScore(Hit hit) {
        if (!hasAdditionalScoreCount()) {
            throw new CannotCalculateScore();
        }
        return new Score(hit.sumScore(score), useAdditionalCount());
    }

    private int useAdditionalCount() {
        return additionalScoreCount - UNIT_ADDITIONAL_COUNT;
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
