package bowling.domain.pitching;

import bowling.domain.FallenPinNumber;
import bowling.domain.frame.Frame;

import java.util.Objects;

public class SparePitching implements Pitching {

    private FallenPinNumber firstFallenPinNumber;
    private FallenPinNumber secondFallenPinNumber;

    private SparePitching(FallenPinNumber firstFallenPinNumber, FallenPinNumber secondFallenPinNumber) {
        this.firstFallenPinNumber = firstFallenPinNumber;
        this.secondFallenPinNumber = secondFallenPinNumber;
    }

    public static SparePitching of(FallenPinNumber firstFallenPinNumber, FallenPinNumber secondFallenPinNumber) {
        validateMissingPitching(firstFallenPinNumber, secondFallenPinNumber);
        return new SparePitching(firstFallenPinNumber, secondFallenPinNumber);
    }

    private static void validateMissingPitching(FallenPinNumber firstFallenPinNumber, FallenPinNumber secondFallenPinNumber) {
        if (!firstFallenPinNumber.isValidPitching(secondFallenPinNumber)) {
            throw new RuntimeException("두번째 볼링이 잘못된 값입니다.");
        }
    }

    @Override
    public boolean isFinished(Frame frame) {
        return !frame.isFinalFrame();
    }

    @Override
    public Pitching pitch(FallenPinNumber fallenPinNumber) {
        return BonusPitching.of(firstFallenPinNumber, secondFallenPinNumber, fallenPinNumber);
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
