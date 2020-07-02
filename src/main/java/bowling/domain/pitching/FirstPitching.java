package bowling.domain.pitching;

import bowling.domain.FallenPinNumber;
import bowling.domain.Frame;

import java.util.Objects;

public class FirstPitching implements Pitching {

    private FallenPinNumber firstFallenPinNumber;

    private FirstPitching(FallenPinNumber firstFallenPinNumber) {
        this.firstFallenPinNumber = firstFallenPinNumber;
    }

    public static FirstPitching of(FallenPinNumber firstFallenPinNumber) {
        return new FirstPitching(firstFallenPinNumber);
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
    public String getPitchingIdentical() {
        return "FirstPitching";
    }

    @Override
    public String getPitchingDescription() {
        return firstFallenPinNumber.getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstPitching that = (FirstPitching) o;
        return Objects.equals(firstFallenPinNumber, that.firstFallenPinNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstFallenPinNumber);
    }
}
