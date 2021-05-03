package bowling.domain;

import bowling.domain.status.Status;

public class AddedPitch {
    private static final int USE_ONE_BONUS = 1;

    private int addedBonusCount;
    private final Status status;

    public AddedPitch(Status status) {
        this.addedBonusCount = status.bonusPitchCount();
        this.status = status;
    }

    public AddedPitch(int addedBonusCount, Status status) {
        this.addedBonusCount = addedBonusCount;
        this.status = status;
    }

    public int addedBonusCount() {
        return addedBonusCount;
    }

    public void useOneBonusPitch() {
        addedBonusCount -= USE_ONE_BONUS;
    }

    public boolean isEnd() {
        return status.isEnd();
    }

    public String display() {
        return status.display();
    }
}
