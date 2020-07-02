package bowling.domain.pitching;

import bowling.domain.FallenPinNumber;
import bowling.domain.Frame;

import java.util.Objects;

public class SparePitching implements Pitching {

    private FallenPinNumber firstFallenPinNumber;
    private FallenPinNumber secondFallenPinNumber;

    private SparePitching(FallenPinNumber firstFallenPinNumber, FallenPinNumber secondFallenPinNumber) {
        this.firstFallenPinNumber = firstFallenPinNumber;
        this.secondFallenPinNumber = secondFallenPinNumber;
    }

    public static SparePitching of(FallenPinNumber firstFallenPinNumber, FallenPinNumber secondFallenPinNumber) {
        return new SparePitching(firstFallenPinNumber, secondFallenPinNumber);
    }

    @Override
    public boolean isFinished(Frame frame) {
        return frame.isFinalFrame() ? false : true;
    }

    @Override
    public Pitching pitch(FallenPinNumber fallenPinNumber) {
        return BonusPitching.of(firstFallenPinNumber, secondFallenPinNumber, fallenPinNumber);
    }

    @Override
    public String getPitchingIdentical() {
        return "SparePitching";
    }

    @Override
    public String getPitchingDescription() {
        return firstFallenPinNumber.getDescription() + "|/";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SparePitching that = (SparePitching) o;
        return Objects.equals(firstFallenPinNumber, that.firstFallenPinNumber) &&
                Objects.equals(secondFallenPinNumber, that.secondFallenPinNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstFallenPinNumber, secondFallenPinNumber);
    }
}
