package bowling.domain;

import bowling.domain.status.Status;

public class BonusPitch {
    private final int addedBonusCount;
    private final Status status;

    public BonusPitch(Status status) {
        this.addedBonusCount = status.bonusPitchCount();
        this.status = status;
    }

    public BonusPitch(int addedBonusCount, Status status) {
        this.addedBonusCount = addedBonusCount;
        this.status = status;
    }

    public int addedBonusCount() {
        return addedBonusCount;
    }

    public String display() {
        return status.display();
    }
}
