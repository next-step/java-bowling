package bowling.domain;

public class Score {
    private int score;
    private int bonusApplyChance;

    private Score(int score, int bonusApplyChance) {
        this.score = score;
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

    public boolean leftBonusApplyChance() {
        return bonusApplyChance > 0;
    }

    public void addBonusScore(Integer bonusScore) {
        this.score += bonusScore;
        bonusApplyChance--;
    }

    public int getValue() {
        return score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "score=" + score +
                ", bonusApplyChance=" + bonusApplyChance +
                '}';
    }
}
