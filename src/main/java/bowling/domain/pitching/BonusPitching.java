package bowling.domain.pitching;

import bowling.domain.FallenPinNumber;
import bowling.domain.Frame;

import java.util.Objects;

public class BonusPitching implements Pitching {

    private FallenPinNumber firstFallenPinNumber;
    private FallenPinNumber secondFallenPinNumber;
    private FallenPinNumber finalFallenPinNumber;

    private BonusPitching(FallenPinNumber firstFallenPinNumber, FallenPinNumber secondFallenPinNumber, FallenPinNumber finalFallenPinNumber) {
        this.firstFallenPinNumber = firstFallenPinNumber;
        this.secondFallenPinNumber = secondFallenPinNumber;
        this.finalFallenPinNumber = finalFallenPinNumber;
    }

    public static BonusPitching of(FallenPinNumber firstFallenPinNumber, FallenPinNumber secondFallenPinNumber, FallenPinNumber finalFallenPinNumber) {
        return new BonusPitching(firstFallenPinNumber, secondFallenPinNumber, finalFallenPinNumber);
    }

    @Override
    public boolean isFinished(Frame frame) {
        return finalFallenPinNumber != null;
    }

    @Override
    public Pitching pitch(FallenPinNumber fallenPinNumber) {
        return of(firstFallenPinNumber, secondFallenPinNumber, fallenPinNumber);
    }

    @Override
    public String getPitchingIdentical() {
        return "BonusPitching";
    }

    @Override
    public String getPitchingDescription() {
        String secondShape = firstFallenPinNumber.isSpare(secondFallenPinNumber) ? "/" : secondFallenPinNumber.getDescription();
        if (finalFallenPinNumber == null) {
            return firstFallenPinNumber.getDescription() + "|" + secondShape;
        }

        return firstFallenPinNumber.getDescription() + "|" + secondShape + "|" + finalFallenPinNumber.getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BonusPitching that = (BonusPitching) o;
        return Objects.equals(firstFallenPinNumber, that.firstFallenPinNumber) &&
                Objects.equals(secondFallenPinNumber, that.secondFallenPinNumber) &&
                Objects.equals(finalFallenPinNumber, that.finalFallenPinNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstFallenPinNumber, secondFallenPinNumber, finalFallenPinNumber);
    }
}
