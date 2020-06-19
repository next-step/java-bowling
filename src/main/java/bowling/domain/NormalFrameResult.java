package bowling.domain;

public class NormalFrameResult implements FrameResult {
    private int firstNumberOfHitPin;

    NormalFrameResult(int firstNumberOfHitPin) {
        this.firstNumberOfHitPin = firstNumberOfHitPin;
    }

    public static NormalFrameResult firstThrow(int numberOfHitPin) {
        return new NormalFrameResult(numberOfHitPin);
    }

    @Override
    public boolean isStrike() {
        return false;
    }
}
