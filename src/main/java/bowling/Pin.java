package bowling;

public class Pin {

    public static final int MAX_PIN_COUNT = 10;
    public static final int MIN_PIN_COUNT = 0;

    private final int pinCount;

    private Pin(final int pinCount) {
        validateRange(pinCount);
        this.pinCount = pinCount;
    }

    private void validateRange(final int pinCount) {
        if (pinCount < MIN_PIN_COUNT || pinCount > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("Pin count must be between zero and 10.");
        }
    }

    public static Pin of(final int pinCount) {
        return new Pin(pinCount);
    }

    public Pin sum(final Pin pinCount) {
        return new Pin(this.pinCount + pinCount.pinCount);
    }

    public boolean isEqualTo(final int pinCount) {
        return this.pinCount == pinCount;
    }

    public Score toScore() {
        return Score.of(pinCount);
    }

    @Override
    public String toString() {
        return String.valueOf(pinCount);
    }
}
