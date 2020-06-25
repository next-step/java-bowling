package bowling.game;

public class Pitch {
    private static final int PIN_COUNT_MIN = 0;
    private static final int PIN_COUNT_MAX = 10;

    private final int pinCount;
    private final State state;

    public Pitch(final int pinCount, final int leftPinCount) {
        validatePinCount(pinCount);
        validateLeftPinCount(leftPinCount);
        this.pinCount = pinCount;
        this.state = findState(pinCount, leftPinCount);
    }

    private void validatePinCount(final int pinCount) {
        if (pinCount < PIN_COUNT_MIN || pinCount > PIN_COUNT_MAX) {
            throw new IllegalArgumentException("쓰러진 핀의 갯수는 0 ~ 10 사이어야 합니다. - " + pinCount);
        }
    }

    private void validateLeftPinCount(final int leftPinCount) {
        if (leftPinCount < PIN_COUNT_MIN || leftPinCount > PIN_COUNT_MAX) {
            throw new IllegalArgumentException("남은 핀의 갯수는 0 ~ 10 사이어야 합니다. - " + leftPinCount);
        }
    }

    private State findState(final int pinCount, final int leftPinCount) {
        if (pinCount == PIN_COUNT_MAX) {
            return State.STRIKE;
        }

        if (leftPinCount == PIN_COUNT_MIN) {
            return State.SPARE;
        }

        if (pinCount == PIN_COUNT_MIN) {
            return State.GUTTER;
        }

        return State.MISS;
    }

    public int getPinCount() {
        return pinCount;
    }

    public State getState() {
        return state;
    }
}
