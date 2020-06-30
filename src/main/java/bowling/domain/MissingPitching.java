package bowling.domain;

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

    public Pitching toSparePitching(FallenPinNumber secondFallenPinNumber) {
        return SparePitching.of(firstFallenPinNumber, secondFallenPinNumber);
    }

    @Override
    public boolean isOccupation() {
        return true;
    }
}
