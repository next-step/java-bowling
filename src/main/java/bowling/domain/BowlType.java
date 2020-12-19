package bowling.domain;

public enum BowlType {

    STRIKE(2, "X"),
    SPARED(1, "/"),
    MISS(0, ""),
    READY(0, ""),
    NORMAL(0, "");

    private int bonusCount;
    private String mark;

    BowlType(int bonusCount, String mark) {
        this.bonusCount = bonusCount;
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }

    public int getBonusCount() {
        return bonusCount;
    }
}
