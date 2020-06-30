package bowling.domain;

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

}
