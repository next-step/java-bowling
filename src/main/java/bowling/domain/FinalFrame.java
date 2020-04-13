package bowling.domain;

import java.util.Objects;

public class FinalFrame extends NormalFrame {
    private int bonusCount = 0;
    private Pin bonusPin;

    public FinalFrame(int frameNum) {
        super(frameNum);
    }

    public FinalFrame(int frameNum, boolean bonusFlag) {
        super(frameNum);
        if (bonusFlag) {
            this.bonusCount = 1;
        }
    }

    public int bonusBowl(int falledPins) {
        if (bonusCount < 1) {
            throw new IllegalArgumentException("보너스로 볼을 투구 할 수 없습니다.");
        }
        bonusPin = Pin.of(falledPins);
        return falledPins;
    }

    public Pin getBonusPin() {
        return bonusPin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof FinalFrame)) { return false; }
        if (!super.equals(o)) { return false; }
        final FinalFrame that = (FinalFrame) o;
        return Objects.equals(getBonusPin(), that.getBonusPin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getBonusPin());
    }
}
