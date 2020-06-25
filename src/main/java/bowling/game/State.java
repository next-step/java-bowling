package bowling.game;

public class State {
    private static final int PIN_COUNT_MIN = 0;
    private static final int PIN_COUNT_MAX = 10;
    private final int pinCount;

    public State(final int pinCount) {
        validatePinCount(pinCount);
        this.pinCount = pinCount;
    }

    private void validatePinCount(final int pinCount) {
        if (pinCount < PIN_COUNT_MIN || pinCount > PIN_COUNT_MAX) {
            throw new IllegalArgumentException("쓰러진 핀의 갯수는 0 ~ 10 사이어야 합니다. - " + pinCount);
        }
    }

    public int getPinCount() {
        return pinCount;
    }
}
