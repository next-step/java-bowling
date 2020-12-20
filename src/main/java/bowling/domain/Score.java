package bowling.domain;

public class Score {
    private final int value;
    private final int bonusApplyChance;

    private Score(int value, int bonusApplyChance) {
        this.value = value;
        this.bonusApplyChance = bonusApplyChance;
    }

    public static Score ofStrike() {
        return new Score(10, 2);
    }

    public static Score ofSpare() {
        return new Score(10, 1);
    }

    public static Score ofMiss(int score) {
        return new Score(score, 0);
    }

    public static Score of(int score, int bonusApplyChance) {
        return new Score(score, bonusApplyChance);
    }

    public boolean leftBonusApplyChance() {
        return bonusApplyChance > 0;
    }

    public Score addBonusScore(int bonusScore) {
        return new Score(value + bonusScore, bonusApplyChance - 1);
    }

    public Score addScore(int score) {
        return new Score(value + score, bonusApplyChance);
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Score{" +
                "score=" + value +
                ", bonusApplyChance=" + bonusApplyChance +
                '}';
    }
}
