package bowling.game;

public class Pitch {
    private static final int PIN_COUNT_MIN = 0;
    private static final int PIN_COUNT_MAX = 10;

    private final int pinCount;
    private final State state;

    private Pitch(final int pinCount, final State state) {
        validatePinCount(pinCount);
        this.pinCount = pinCount;
        this.state = state;
    }

    public static Pitch firstPitch(final int pinCount) {
        if (pinCount == PIN_COUNT_MAX) {
            return new Pitch(pinCount, State.STRIKE);
        }

        return new Pitch(pinCount, findState(pinCount));
    }

    private static State findState(final int pinCount) {
        if (pinCount == PIN_COUNT_MIN) {
            return State.GUTTER;
        }

        return State.MISS;
    }

    private void validatePinCount(final int pinCount) {
        if (pinCount < PIN_COUNT_MIN || pinCount > PIN_COUNT_MAX) {
            throw new IllegalArgumentException("쓰러진 핀의 갯수는 0 ~ 10 사이어야 합니다. - " + pinCount);
        }
    }

    public Pitch nextPitch(final int pinCount) {
        validateNextPinCount(pinCount);
        if (pinCount + this.pinCount == PIN_COUNT_MAX) {
            return new Pitch(pinCount, State.SPARE);
        }

        return new Pitch(pinCount, findState(pinCount));
    }

    private void validateNextPinCount(final int pinCount) {
        if (this.pinCount + pinCount > PIN_COUNT_MAX) {
            throw new IllegalArgumentException(String.format("쓰러트릴 핀의 갯수보다 남은 핀의 갯수가 적습니다. 남은 핀 - %d", this.pinCount));
        }
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
