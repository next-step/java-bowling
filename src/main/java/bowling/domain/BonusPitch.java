package bowling.domain;

import bowling.domain.status.Status;

public class BonusPitch {
    private static final int USE_ONE_BONUS = 1;

    private int addedBonusCount;
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

    public void useOneBonusPitch() {
        addedBonusCount -= USE_ONE_BONUS;
    }

    public String display() {
        return status.display();
    }
}
