package bowling.domain;

public class StrikePitching implements Pitching {

    private FallenPinNumber firstFallenPinNumber;

    private StrikePitching(FallenPinNumber firstFallenPinNumber) {
        this.firstFallenPinNumber = firstFallenPinNumber;
    }

    public static StrikePitching of(FallenPinNumber firstFallenPinNumber) {
        return new StrikePitching(firstFallenPinNumber);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
