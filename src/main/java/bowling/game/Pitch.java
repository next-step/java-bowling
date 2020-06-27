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

    public static Pitch firstPitch(final int pinCount) {
        return new Pitch(pinCount, PIN_COUNT_MAX - pinCount);
    }

    public Pitch nextPitch(final int pinCount) {
        return new Pitch(pinCount, PIN_COUNT_MAX - this.pinCount - pinCount);
    }

    private void validatePinCount(final int pinCount) {
        if (pinCount < PIN_COUNT_MIN || pinCount > PIN_COUNT_MAX) {
            throw new IllegalArgumentException("쓰러진 핀의 갯수는 0 ~ 10 사이어야 합니다. - " + pinCount);
        }
    }

    private void validateLeftPinCount(final int leftPinCount) {
        if (leftPinCount < PIN_COUNT_MIN || leftPinCount > PIN_COUNT_MAX) {
            throw new IllegalArgumentException("쓰러트릴 핀의 갯수보다 남은 핀의 갯수가 적습니다. 남게될 핀 - " + leftPinCount);
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

    public String stateToString() {
        if (this.state == State.MISS) {
            return String.valueOf(pinCount);
        }

        return this.state.getSymbol();
    }
}
