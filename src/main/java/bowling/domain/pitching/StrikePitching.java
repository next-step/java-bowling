package bowling.domain.pitching;

import bowling.domain.FallenPinNumber;
import bowling.domain.Frame;

public class StrikePitching implements Pitching {

    private FallenPinNumber firstFallenPinNumber;
    private FallenPinNumber secondFallenPinNumber;

    private StrikePitching(FallenPinNumber firstFallenPinNumber, FallenPinNumber secondFallenPinNumber) {
        this.firstFallenPinNumber = firstFallenPinNumber;
        this.secondFallenPinNumber = secondFallenPinNumber;
    }

    public static StrikePitching of(FallenPinNumber firstFallenPinNumber) {
        return of(firstFallenPinNumber, null);
    }

    public static StrikePitching of(FallenPinNumber firstFallenPinNumber, FallenPinNumber secondFallenPinNumber) {
        return new StrikePitching(firstFallenPinNumber, secondFallenPinNumber);
    }

    @Override
    public boolean isFinished(Frame frame) {
        if (!frame.isFinalFrame()) {
            return true;
        }

        return false;
    }

    @Override
    public Pitching pitch(FallenPinNumber fallenPinNumber) {
        return BonusPitching.of(firstFallenPinNumber, fallenPinNumber, null);
    }

    @Override
    public String getPitchingDescription() {
        return "X";
    }
}
