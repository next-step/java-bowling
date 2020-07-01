package bowling.domain.pitching;

import bowling.domain.FallenPinNumber;
import bowling.domain.Frame;

public class FirstPitching implements Pitching {

    private FallenPinNumber firstFallenPinNumber;

    private FirstPitching(FallenPinNumber firstFallenPinNumber) {
        this.firstFallenPinNumber = firstFallenPinNumber;
    }

    public static FirstPitching of(FallenPinNumber firstFallenPinNumber) {
        return new FirstPitching(firstFallenPinNumber);
    }

    public Pitching toMissingPitching(FallenPinNumber secondFallenPinNumber) {
        return MissingPitching.of(firstFallenPinNumber, secondFallenPinNumber);
    }

    public Pitching toSparePitching(FallenPinNumber secondFallenPinNumber) {
        return SparePitching.of(firstFallenPinNumber, secondFallenPinNumber);
    }

    @Override
    public Pitching pitch(FallenPinNumber fallenPinNumber) {
        if (fallenPinNumber.isSpare(firstFallenPinNumber)) {
            return SparePitching.of(firstFallenPinNumber, fallenPinNumber);
        }

        return MissingPitching.of(firstFallenPinNumber, fallenPinNumber);
    }

    @Override
    public boolean isFinished(Frame frame) {
        return false;
    }

    @Override
    public boolean bonusPitching() {
        return false;
    }
}
