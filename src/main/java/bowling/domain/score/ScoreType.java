package bowling.domain.score;

public enum ScoreType {
    STRIKE(2),
    SPARE(1),
    STRIKE_AND_SPARE(0),
    MISS(0),
    READY(0);

    private int bonusBowlCount;

    ScoreType(int bonusBowlCount) {
        this.bonusBowlCount = bonusBowlCount;
    }

    public int getBonusBowlCount() {
        return bonusBowlCount;
    }
}
