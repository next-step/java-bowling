package bowling.domain;

public class FinalFrame extends NormalFrame {
    private int bonusCount = 0;

    public FinalFrame(int frameNum) {
        super(frameNum);
    }

    public FinalFrame(int frameNum, boolean bonusFlag) {
        super(frameNum);
        if (bonusFlag) {
            bonusCount = 1;
            getRolls().addBonusRoll();
        }
    }

    public int bonusBowl(int falledPins) {
        if (bonusCount < 1) {
            throw new IllegalArgumentException("보너스로 볼을 투구 할 수 없습니다.");
        }
        getRolls().add(Pin.of(falledPins));
        return falledPins;
    }

    public int getBonusCount() {
        return bonusCount;
    }
}
