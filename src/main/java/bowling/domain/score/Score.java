package bowling.domain.score;

import java.util.Objects;

public class Score {
    private static final int DEFAULT_COUNT = 0;
    private static final int BONUS_COUNT_OF_SPARE = 1;
    private static final int BONUS_COUNT_OF_STRIKE = 2;
    private static final int MAX_SCORE = 10;

    private int score;
    private final int bonusCount;

    private Score(int score, int bonusCount) {
        this.score = score;
        this.bonusCount = bonusCount;
    }

    public static Score ofStrike() {
        return new Score(MAX_SCORE, BONUS_COUNT_OF_STRIKE);
    }

    public static Score ofSpare() {
        return new Score(MAX_SCORE, BONUS_COUNT_OF_SPARE);
    }

    public static Score ofMiss(int score) {
        return new Score(score, DEFAULT_COUNT);
    }

    public static Score from(int score) {
        return new Score(score, 0);
    }

    public Score next(int score) {
        return new Score(this.score + score, this.bonusCount - 1);
    }

    public boolean calculated() {
        return this.bonusCount == DEFAULT_COUNT;
    }

    public int score() {
        return score;
    }

    public int bonusCount() {
        return bonusCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score &&
                bonusCount == score1.bonusCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, bonusCount);
    }
}
