package bowling.domain;

import java.util.Objects;

public class FinalFrame implements Frame {
    private static final int MAX_FRAME_NUM = 10;
    private int bonusCount = 0;
    private int falledPins = 0;
    private int frameNum;
    private Rolls rolls;

    public FinalFrame(boolean bonusFlag) {
        frameNum = MAX_FRAME_NUM;
        this.rolls = new Rolls();
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

    @Override
    public int getFrameNum() {
        return frameNum;
    }

    @Override
    public int bowl(int falledPins) {
        this.falledPins += falledPins;
        rolls.add(Pin.of(falledPins));
        return falledPins;
    }

    @Override
    public Rolls getRolls() {
        return rolls;
    }

    @Override
    public int getFalledPins() {
        return falledPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof FinalFrame)) { return false; }
        final FinalFrame that = (FinalFrame) o;
        return getBonusCount() == that.getBonusCount() &&
               getFalledPins() == that.getFalledPins() &&
               getFrameNum() == that.getFrameNum() &&
               Objects.equals(getRolls(), that.getRolls());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrameNum(), getRolls());
    }
}
