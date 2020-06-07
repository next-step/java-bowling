package bowling.domain;

public class Roll {
    private static final int MAXIMUM_PIN_NUMBER = 10;
    public static final String PIN_LIMIT_ERROR = "10개보다 핀이 더 생길 수 없습니다.";

    private final int pin;

    public Roll(final int pin) {
        this.pin = validatePin(pin);
    }

    private int validatePin(int pin) {
        if (pin > MAXIMUM_PIN_NUMBER) {
            throw new IllegalArgumentException(PIN_LIMIT_ERROR);
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
