package bowling.domain.score;

public enum  ScoreType2 {
    STRIKE(2),
    SPARE(1),
    NORMAL(0),
    MISS(0),
    READY(0);

    private int bonusRollCount;

    ScoreType2(int bonusRollCount) {
        this.bonusRollCount = bonusRollCount;
    }

    public int getBonusRollCount() {
        return bonusRollCount;
    }
}
