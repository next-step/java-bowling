package bowling.domain;

import bowling.domain.status.Status;

public class BonusPitch {
    private final int addedBonusCount;
    private final Status status;

    public BonusPitch(int addedBonusCount, Status status) {
        this.addedBonusCount = addedBonusCount;
        this.status = status;
    }

    public boolean isAbleToPitch() {
        return addedBonusCount - 1 > 0;
    }

    public String display() {
        return status.display();
    }
}
