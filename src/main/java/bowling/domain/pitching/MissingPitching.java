package bowling.domain.pitching;

import bowling.domain.FallenPinNumber;
import bowling.domain.Frame;

import java.util.Objects;

public class MissingPitching implements Pitching {

    private FallenPinNumber firstFallenPinNumber;
    private FallenPinNumber secondFallenPinNumber;

    private MissingPitching(FallenPinNumber firstFallenPinNumber, FallenPinNumber secondFallenPinNumber) {
        this.firstFallenPinNumber = firstFallenPinNumber;
        this.secondFallenPinNumber = secondFallenPinNumber;
    }

    public static MissingPitching of(FallenPinNumber firstFallenPinNumber, FallenPinNumber secondFallenPinNumber) {
        return new MissingPitching(firstFallenPinNumber, secondFallenPinNumber);
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
    public String getPitchingIdentical() {
        return "MissingPitching";
    }

    @Override
    public String getPitchingDescription() {
        return firstFallenPinNumber.getDescription() + "|" + secondFallenPinNumber.getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MissingPitching that = (MissingPitching) o;
        return Objects.equals(firstFallenPinNumber, that.firstFallenPinNumber) &&
                Objects.equals(secondFallenPinNumber, that.secondFallenPinNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstFallenPinNumber, secondFallenPinNumber);
    }
}
