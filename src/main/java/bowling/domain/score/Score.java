package bowling.domain.score;

import java.util.Objects;

public class Score {

    private static final Score NONE_SCORE = new Score(10_000, 10_000);
    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE_ON_ONE_PIN = 10;

    private static final int MIN_BONUS_CHANCE = 0;
    private static final int BONUS_CHANCE_OF_SPARE = 1;
    private static final int BONUS_CHANCE_OF_STRIKE = 2;
    private static final int BASE_BONUS_CHANCE = 100_000;

    private int value;
    private int bonusChance;
    private Score nextScore;

    public Score(int value) {
        this(value, MIN_BONUS_CHANCE);
    }

    public Score(int value, int bonusChance) {
        this(value, NONE_SCORE, bonusChance);
    }

    public Score(int value, Score nextScore, int bonusChance) {
        checkArguments(value, bonusChance);
        this.value = value;
        this.nextScore = nextScore;
        this.bonusChance = bonusChance;
    }

    private void checkArguments(int value, int bonusChance) {
        if (value < MIN_SCORE) {
            throw new IllegalScoreException("점수가 음수일 수 없습니다.");
        }
        if (bonusChance < MIN_BONUS_CHANCE) {
            throw new IllegalScoreException("보너스 기회가 음수일 수 없습니다.");
        }
    }

    public static Score base() {
        return new Score(MIN_SCORE, BASE_BONUS_CHANCE);
    }

    public static Score gutter() {
        return new Score(MIN_SCORE, MIN_BONUS_CHANCE);
    }

    public static Score spare() {
        return new Score(MAX_SCORE_ON_ONE_PIN, BONUS_CHANCE_OF_SPARE);
    }

    public static Score strike() {
        return new Score(MAX_SCORE_ON_ONE_PIN, BONUS_CHANCE_OF_STRIKE);
    }

    public Score next() {
        nextScore = new Score(value, NONE_SCORE, BASE_BONUS_CHANCE);
        return nextScore;
    }

    public void add(Score other) {
        value += other.value;
        bonusChance = other.bonusChance;
    }

    public void addBonus(Score bonusScore) {
        if (bonusChance == MIN_BONUS_CHANCE) {
            return;
        }
        bonusChance--;
        value += bonusScore.value;
        reflectAddedScoreIntoNextScore(bonusScore);
    }

    private void reflectAddedScoreIntoNextScore(Score addedScore) {
        if (nextScore.equals(NONE_SCORE)) {
            return;
        }
        nextScore.value += addedScore.value;
        nextScore.reflectAddedScoreIntoNextScore(addedScore);
    }

    public int value() {
        if (canCalculate()) {
            return value;
        }
        throw new CanNotCalculateException();
    }

    public boolean canCalculate() {
        return bonusChance == MIN_BONUS_CHANCE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value == score.value && bonusChance == score.bonusChance && Objects.equals(nextScore, score.nextScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, bonusChance, nextScore);
    }

    @Override
    public String toString() {
        return "Score{" +
                "value=" + value +
                ", bonusChance=" + bonusChance +
                '}';
    }
}
