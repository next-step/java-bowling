package bowling.domain.pitching;

import bowling.domain.FallenPinNumber;
import bowling.domain.Frame;

public class BonusPitching implements Pitching {

    private FallenPinNumber fallenPinNumber;

    private BonusPitching(FallenPinNumber fallenPinNumber) {
        this.fallenPinNumber = fallenPinNumber;
    }

    public static BonusPitching of(FallenPinNumber fallenPinNumber) {
        return new BonusPitching(fallenPinNumber);
    }

    @Override
    public boolean isFinished(Frame frame) {
        return true;
    }

    @Override
    public Pitching pitch(FallenPinNumber fallenPinNumber) {
        throw new RuntimeException("이미 끝난 프레임입니다.");
    }

    @Override
    public boolean bonusPitching() {
        return false;
    }
}
