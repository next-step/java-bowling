package bowling.domain;

import java.util.Objects;
import java.util.OptionalInt;

public class Score {

    public static final int MAXIMUM_SCORE = 10;
    private static final int MINIMUM_SCORE = 0;
    private static final int ZERO_BONUS_COUNT = 0;
    private static final int STRIKE_BONUS_COUNT = 2;
    private static final int SPARE_BONUS_COUNT = 1;

    private final int score;
    private final int bonusCount;

    public Score() {
        this(MINIMUM_SCORE, ZERO_BONUS_COUNT);
    }

    public Score(int hitCount) {
        this(hitCount, ZERO_BONUS_COUNT);
    }

    public Score(int score, int bonusCount) {
        this.score = score;
        this.bonusCount = bonusCount;
    }

    public static Score of(Score score, Score previousScore) {
        return new Score(score.score + previousScore.score, previousScore.bonusCount-1);
    }

    public static Score ofStrike() {
        return new Score(MAXIMUM_SCORE, STRIKE_BONUS_COUNT);
    }

    public static Score ofSpare() {
        return new Score(MAXIMUM_SCORE, SPARE_BONUS_COUNT);
    }

    public int get() {
        return this.score;
    }

    public OptionalInt getAsOptionalInt() {
        return OptionalInt.of(score);
    }

    public boolean isAddedAllBonus() {
        return bonusCount == ZERO_BONUS_COUNT;
    }

    public Score add(int hitCount) {
        return new Score(this.score + hitCount, this.bonusCount-1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score1 = (Score) o;
        return score == score1.score && bonusCount == score1.bonusCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, bonusCount);
    }

    public boolean isSpareWith(Score otherScore) {
        return this.score + otherScore.score == MAXIMUM_SCORE;
    }

    public boolean isStrike() {
        return this.score == MAXIMUM_SCORE;
    }

    public boolean isMiss() {
        return this.score == MINIMUM_SCORE;
    }

    public Score add(Score otherScore) {
        return new Score(this.score + otherScore.score, this.bonusCount-1);
    }

    @Override
    public String toString() {
        return Integer.toString(score);
    }
}
