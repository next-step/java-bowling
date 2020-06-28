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
        State state = State.findState(pinCount, PIN_COUNT_MIN, false);

        return new Pitch(pinCount, state);
    }

    private void validatePinCount(final int pinCount) {
        if (pinCount < PIN_COUNT_MIN || pinCount > PIN_COUNT_MAX) {
            throw new IllegalArgumentException("쓰러진 핀의 갯수는 0 ~ 10 사이어야 합니다. - " + pinCount);
        }
    }

    public Pitch nextPitch(final int pinCount) {
        validateNextPinCount(pinCount);

        State state = State.findState(pinCount, this.pinCount, true);

        return new Pitch(pinCount, state);
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

    public boolean isStrikePitch() {
        return state.isStrike();
    }

    public boolean isSparePitch() {
        return state.isSpare();
    }

    public String stateToString() {
        if (state.isMiss()) {
            return String.valueOf(pinCount);
        }

        return this.state.getSymbol();
    }
}
