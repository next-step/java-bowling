package bowling.domain.frame;

public class NormalFrame {
    private static final int KNOCKED_DOWN_PIN_COUNT_DEFAULT = -1;
    private static final int KNOCKED_DOWN_PIN_COUNT_STRIKE = 10;
    private static final int KNOCKED_DOWN_PIN_COUNT_GUTTER = 0;

    private Integer firstRollingResult;
    private Integer secondRollingResult;

    public NormalFrame() {
        this.firstRollingResult = KNOCKED_DOWN_PIN_COUNT_DEFAULT;
        this.secondRollingResult = KNOCKED_DOWN_PIN_COUNT_DEFAULT;
    }

    public void rollingBall(int pinCount) {
        if (firstRollingResult == KNOCKED_DOWN_PIN_COUNT_DEFAULT) {
            this.firstRollingResult = pinCount;
            return;
        }

        this.secondRollingResult = pinCount;
    }

    public boolean isRollable() {
        if (firstRollingResult == KNOCKED_DOWN_PIN_COUNT_STRIKE) {
            return false;
        }

        return true;
    }
}
