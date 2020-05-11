package bowling.domain.frame;

public class FinalFrame implements  Frame {
    private final FinalRollingResult rollingResult;

    public FinalFrame() {
        this.rollingResult = FinalRollingResult.init();
    }

    public void rollingBall(int pinCount) {
        rollingResult.roll(pinCount);
    }

    public boolean isRollable() {
        return rollingResult.isRollable();
    }
}
