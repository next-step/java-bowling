package bowling.domain;

public enum BowlType {

    STRIKE(2),
    SPARED(1),
    MISS(0),
    NONE(0),
    END(0);

    private int bonusCount;

    BowlType(int bonusCount) {
        this.bonusCount = bonusCount;
    }


    public int getBonusCount() {
        return bonusCount;
    }
}
