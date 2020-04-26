package bowling.domain.scoreType;

class ClearProvider implements StatusProvider {
    private final int bonusCount;

    ClearProvider(int bonusCount) {
        this.bonusCount = bonusCount;
    }

    public boolean isFinished() {
        return true;
    }

    public boolean isClear() {
        return true;
    }

    public int getBonusCount() {
        return bonusCount;
    }
}
