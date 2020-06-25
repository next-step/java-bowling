package bowling.domain;

public class Roll {
    public static final int MAXIMUM_PIN_NUMBER = 10;
    public static final int MINIMUM_PIN_NUMBER = 2;
    public static final String PIN_MAX_ERROR = "10개보다 핀이 더 생길 수 없습니다.";
    public static final String PIN_MIN_ERROR = "볼링 핀은 최초 0 미만이 될 수 없습니다. ";

    private final int pin;

    public Roll(final int pin) {
        this.pin = validatePin(pin);
    }

    private int validatePin(int pin) {
        if (pin > MAXIMUM_PIN_NUMBER) {
            throw new IllegalArgumentException(PIN_MAX_ERROR);
        }

        if (pin < MINIMUM_PIN_NUMBER) {
            throw new IllegalArgumentException(PIN_MIN_ERROR);
        }
        return pin;
    }

    public Boolean isStrike() {
        return pin == MAXIMUM_PIN_NUMBER;
    }

    public int getPin() {
        return pin;
    }

}
