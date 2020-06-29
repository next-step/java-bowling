package bowling.domain.bonus;

public class BonusScoreInfo {
    private final int bonusCount;
    private final int frameIndex;

    public BonusScoreInfo(int bonusCount, int frameIndex) {
        this.bonusCount = bonusCount;
        this.frameIndex = frameIndex;
    }

    public int getBonusCount() {
        return bonusCount;
    }

    public int getFrameIndex() {
        return frameIndex;
    }
}
