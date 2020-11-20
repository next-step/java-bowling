package bowling.domain.score;

public enum ScoreType {
    STRIKE(2),
    SPARE(1),
    NORMAL(0),
    MISS(0),
    READY(0);

    private int bonusRollCount;

    ScoreType(int bonusRollCount) {
        this.bonusRollCount = bonusRollCount;
    }

    public int getBonusRollCount() {
        return bonusRollCount;
    }
}
