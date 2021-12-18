package bowling.domain.score;

import java.util.Objects;

public class Score {

    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE_ON_ONE_PIN = 10;

    private static final int MIN_BONUS_CHANCE = 0;
    private static final int BONUS_CHANCE_OF_SPARE = 1;
    private static final int BONUS_CHANCE_OF_STRIKE = 2;

    private int value;
    private int bonusChance;

    public Score(int value) {
        this(value, MIN_BONUS_CHANCE);
    }

    public Score(int value, int bonusChance) {
        checkArguments(value, bonusChance);
        this.value = value;
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
        return new Score(MIN_SCORE, MIN_BONUS_CHANCE);
    }

    public static Score spare() {
        return new Score(MAX_SCORE_ON_ONE_PIN, BONUS_CHANCE_OF_SPARE);
    }

    public static Score strike() {
        return new Score(MAX_SCORE_ON_ONE_PIN, BONUS_CHANCE_OF_STRIKE);
    }

    public Score next() {
        return new Score(value);
    }

    public void add(Score other) {
        value += other.value;
        bonusChance += other.bonusChance;
    }

    /**
     * @return 추가된 점수
     */
    public Score addBonus(Score other) {
        if (bonusChance == MIN_BONUS_CHANCE) {
            return base();
        }
        bonusChance--;
        value += other.value;
        return new Score(other.value);
    }

    public boolean canCalculate() {
        return bonusChance == MIN_BONUS_CHANCE;
    }

    public int value() {
        if (canCalculate()) {
            return value;
        }
        throw new CanNotCalculateException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return value == score1.value && bonusChance == score1.bonusChance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, bonusChance);
    }

    @Override
    public String toString() {
        return "Score{" +
                "value=" + value +
                ", bonusChance=" + bonusChance +
                '}';
    }
}
