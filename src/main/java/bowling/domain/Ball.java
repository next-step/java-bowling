package bowling.domain;

public class Ball {
    public static final int ZERO_PIN_COUNT = 0;
    public static final int ALL_PIN_COUNT = 10;
    private static final String PIN_SIZE_ERROR_MESSAGE = "Ball은 10개 이하의 pin만 갖을 수 있습니다.";
    private static final String ZERO_TEXT = "-";
    private static final String STRIKE_TEXT = "X";
    private int pin;

    public Ball(int pin) {
        if (pin > ALL_PIN_COUNT) {
            throw new IllegalArgumentException(PIN_SIZE_ERROR_MESSAGE);
        }

        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }

    public boolean isStrike() {
        return pin == ALL_PIN_COUNT;
    }

    @Override
    public String toString() {
        if (pin == ZERO_PIN_COUNT) {
            return ZERO_TEXT;
        }

        if (pin == ALL_PIN_COUNT) {
            return STRIKE_TEXT;
        }

        return String.valueOf(pin);
    }
}
