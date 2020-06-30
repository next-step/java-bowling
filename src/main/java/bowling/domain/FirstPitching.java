package bowling.domain;

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

    public boolean isStrike() {
        return firstFallenPinNumber.isMaxFallenPin();
    }
}
