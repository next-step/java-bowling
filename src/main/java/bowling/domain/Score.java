package bowling.domain;

import java.util.Objects;

public class Score {
    private static final int DEFAULT_COUNT = 0;
    private static final int BONUS_COUNT_OF_SPARE = 1;
    private static final int BONUS_COUNT_OF_STRIKE = 2;
    private static final int MAX_SCORE = 10;

    private int score;
    private final int bonusCount;

    private Score() {
        this(DEFAULT_COUNT, DEFAULT_COUNT);
    }

    private Score(int score, int bonusCount) {
        this.score = score;
        this.bonusCount = bonusCount;
    }

    public static Score init() {
        return new Score();
    }

    public static Score of(int score, int bonusCount) {
        return new Score(score, bonusCount);
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

    public Score next(int score) {
        return new Score(this.score + score, this.bonusCount - 1);
    }

    public Score next(int score, int bonusCount) {
        return new Score(this.score + score, bonusCount);
    }

    public Score next(Score score) {
        return new Score(this.score + score.score, this.bonusCount - 1);
    }

    public Score next(Score score, int bonusCount) {
        return new Score(this.score + score.score, bonusCount);
    }

    public Score add(Score score) {
        return new Score(this.score + score.score, this.bonusCount + score.bonusCount);
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
