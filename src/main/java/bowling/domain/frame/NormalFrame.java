package bowling.domain.frame;

public class NormalFrame implements Frame {
    RollingResult rollingResult;

    public NormalFrame() {
        rollingResult = RollingResult.init();
    }

    public void rollingBall(int pinCount) {
        rollingResult.roll(pinCount);
    }

    public boolean isRollable() {
        return rollingResult.isRollable();
    }

}
