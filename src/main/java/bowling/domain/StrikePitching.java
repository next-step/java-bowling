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
    public boolean isFinished(Frame frame) {
        return true;
    }

    @Override
    public Pitching pitch(FallenPinNumber fallenPinNumber) {
        throw new RuntimeException("이미 끝난 프레임입니다.");
    }
}
