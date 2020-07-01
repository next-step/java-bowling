package bowling.domain;

public class FirstPitching implements Pitching {

    private FallenPinNumber firstFallenPinNumber;

    private FirstPitching(FallenPinNumber firstFallenPinNumber) {
        this.firstFallenPinNumber = firstFallenPinNumber;
    }

    public static FirstPitching of(FallenPinNumber firstFallenPinNumber) {
        return new FirstPitching(firstFallenPinNumber);
    }

    public static FirstPitching init() {
        return of(FallenPinNumber.of(0));
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
    public boolean isFinished() {
        return false;
    }
}
