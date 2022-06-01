package bowling.domain;

import java.util.Objects;
import java.util.OptionalInt;

public class Score {

    public static final int DEFAULT_SCORE = 0;

    private final int score;
    private final int bonusCount;

    public Score() {
        this(DEFAULT_SCORE, 0);
    }

    public Score(int hitCount) {
        this(hitCount, 0);
    }

    public Score(int score, int bonusCount) {
        this.score = score;
        this.bonusCount = bonusCount;
    }

    public static Score of(Score score, Score previousScore) {
        return new Score(score.score + previousScore.score, previousScore.bonusCount-1);
    }

    public static Score ofStrike() {
        return new Score(10, 2);
    }

    public static Score ofSpare() {
        return new Score(10, 1);
    }

    public int get() {
        return this.score;
    }

    public OptionalInt getAsOptionalInt() {
        return OptionalInt.of(score);
    }

    public boolean isAddedAllBonus() {
        return bonusCount == 0;
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
}
