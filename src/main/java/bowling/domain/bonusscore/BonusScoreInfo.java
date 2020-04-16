package bowling.domain.bonusscore;

public class BonusScoreInfo {
    private final int frameIndex;
    private final int bonusCount;

    public BonusScoreInfo(int frameIndex, int bonusCount) {
        this.frameIndex = frameIndex;
        this.bonusCount = bonusCount;
    }

    public int getBonusCount() {
        return bonusCount;
    }

    public boolean isEqualFrameIndex(int frameIndex) {
        return this.frameIndex == frameIndex;
    }
}
